package com.cs.admin.system.auth.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cs.admin.system.auth.domain.dto.OnlinePageDTO;
import com.cs.admin.system.auth.service.OnlineService;
import com.cs.admin.common.config.AuthYamlConfig;
import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.common.util.RequestUtil;
import com.cs.admin.common.util.ResponseUtil;
import com.cs.admin.common.util.TokenUtil;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.covert.SysAdminConvert;
import com.cs.admin.system.manager.domain.vo.AdminVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/28 10:01
 */
@Slf4j
@Service
@AllArgsConstructor
public class OnlineServiceImpl implements OnlineService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final AuthYamlConfig authYamlConfig;

    private final SysAdminConvert sysAdminConvert;


    @Override
    public PageVO<OnlineInfoDTO> page(OnlinePageDTO pageDTO) {

        List<OnlineInfoDTO> list = getAllOnlineInfo(pageDTO.getKey());

        if (ObjectUtil.isNull(list) || list.size() == 0) {
            return PageVO.toVO(pageDTO.getPage(), pageDTO.getLimit(), 0L, null);
        }

        List<OnlineInfoDTO> resultList = toPage(pageDTO.getPage().intValue(), pageDTO.getLimit().intValue(), list);

        if (ObjectUtil.isNotNull(resultList) || resultList.size() == 0) {
            resultList.forEach(x -> x.setPermissions(null));
        }

        return PageVO.toVO(pageDTO.getPage(), pageDTO.getLimit(),
                (long) list.size(), resultList);
    }


    @Override
    public void download(HttpServletResponse response,String keyWord) {

        List<OnlineInfoDTO> onlineInfos = getAllOnlineInfo(keyWord);
        List<Map<String, Object>> rows = new ArrayList<>();

        String title = "在线用户(" + DateUtil.formatDateTime(new Date()) + ")";
        if (ObjectUtil.isNull(onlineInfos) || onlineInfos.size() == 0) {
            ResponseUtil.writeExcel(response, title, title, rows);
            return;
        }

        for (OnlineInfoDTO dto : onlineInfos) {
            Map<String, Object> row = new LinkedHashMap<>(8);
            row.put("用户名", dto.getUserName());

            if (ObjectUtil.isNotNull(dto.getDeptList()) && dto.getDeptList().size() > 0) {
                row.put("部门", dto.getDeptList().get(0).getDeptName());
            }
            row.put("登录IP", dto.getIp());
            row.put("登录地点", dto.getAddress());
            row.put("浏览器", dto.getBrowser());
            row.put("登录日期", dto.getLoginTime());
            rows.add(row);
        }
        ResponseUtil.writeExcel(response, title, title, rows);
    }

    @Override
    public void kickOut(Set<Long> userIds) {

        if (ObjectUtil.isNull(userIds) || userIds.size() == 0) {
            return;
        }

        for (Long userId : userIds) {
            String key = AuthYamlConfig.TOKEN_USER_INFO_PREFIX + userId;
            redisTemplate.delete(key);
        }
    }


    /**
     * 获取所有在线用户
     *
     * @param keyWord 关键字  account | userName
     * @return list
     */
    private List<OnlineInfoDTO> getAllOnlineInfo(String keyWord) {

        Set<String> keys = redisTemplate.keys(AuthYamlConfig.TOKEN_USER_INFO_PREFIX + "*");

        if (ObjectUtil.isNull(keys) || keys.size() == 0) {
            return null;
        }

        List<OnlineInfoDTO> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineInfoDTO infoDTO = (OnlineInfoDTO) redisTemplate.opsForValue().get(key);

            if (ObjectUtil.isNull(infoDTO)) {
                continue;
            }

            if (StrUtil.isNotEmpty(keyWord)) {
                if (infoDTO.getAccount().contains(keyWord)
                        || infoDTO.getUserName().contains(keyWord)) {
                    onlineUsers.add(infoDTO);
                }
            } else {
                onlineUsers.add(infoDTO);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    @Override
    public OnlineInfoDTO getOnlineInfo(DecodedJWT jwt) {
        Long userId = jwt.getClaim(TokenUtil.USER_ID).asLong();
        return (OnlineInfoDTO) redisTemplate.opsForValue().get(getPrefixKey(userId));
    }

    @Override
    public void save(OnlineInfoDTO onlineInfoDTO) {
        redisTemplate.opsForValue().set(getPrefixKey(onlineInfoDTO.getUserId()), onlineInfoDTO, authYamlConfig.getExpire(), TimeUnit.SECONDS);
    }

    @Override
    public void renew(OnlineInfoDTO onlineInfoDTO) {
        LocalDateTime current = LocalDateTime.now();
        if (Duration.between(current, onlineInfoDTO.getRenewTime()).getSeconds() > authYamlConfig.getDetect()) {
            onlineInfoDTO.setRenewTime(current);
            redisTemplate.opsForValue().set(getPrefixKey(onlineInfoDTO.getUserId()), onlineInfoDTO, authYamlConfig.getRenew(), TimeUnit.SECONDS);
        }
    }

    @Override
    public void remove(OnlineInfoDTO onlineInfoDTO) {
        redisTemplate.delete(getPrefixKey(onlineInfoDTO.getUserId()));
    }

    /**
     * 构建在线用户缓存信息
     *
     * @param jwtId       jwtId
     * @param permissions 权限
     * @param adminVO     admin
     * @return online
     */
    @Override
    public OnlineInfoDTO buildOnlineAndSave(String jwtId, Set<String> permissions, AdminVO adminVO) {

        OnlineInfoDTO infoDTO = sysAdminConvert.toDTO(adminVO);

        infoDTO.setJwtId(jwtId);
        LocalDateTime loginTime = LocalDateTime.now();
        infoDTO.setLoginTime(loginTime);
        infoDTO.setRenewTime(loginTime);
        infoDTO.setPermissions(permissions);

        // 设置ip、浏览器类型
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;

        HttpServletRequest request = attributes.getRequest();
        infoDTO.setIp(RequestUtil.getIp(request));
        infoDTO.setBrowser(RequestUtil.getBrowser(request));
        save(infoDTO);
        return infoDTO;
    }


    /**
     * 获取缓存在线用户信息prefix key
     *
     * @param userId 用户ID
     * @return string
     */
    private String getPrefixKey(Long userId) {
        return AuthYamlConfig.TOKEN_USER_INFO_PREFIX + userId;
    }


    /**
     * 分页生产同居
     *
     * @param page 页码
     * @param size 页记录数
     * @param list list
     * @return list
     */
    private List<OnlineInfoDTO> toPage(int page, int size, List<OnlineInfoDTO> list) {
        page = page - 1;
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if (fromIndex > list.size()) {
            return new ArrayList<>();
        } else if (toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }


}
