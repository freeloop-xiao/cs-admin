package com.cs.admin.system.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cs.admin.system.auth.domain.dto.OnlinePageDTO;
import com.cs.admin.common.dto.OnlineInfoDTO;
import com.cs.admin.common.vo.PageVO;
import com.cs.admin.system.manager.domain.vo.AdminVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * <p>
 * 在线用户信息保存业务
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/28 9:58
 */
public interface OnlineService {


    /**
     * 分页查询在线用户
     * @param pageDTO 分页条件
     * @return page
     */
    PageVO<OnlineInfoDTO> page(OnlinePageDTO pageDTO);


    /**
     * 下载在线用户信息
     * @param keyWord 关键字
     * @param response 响应response
     */
    void download(HttpServletResponse response,String keyWord);


    /**
     * 踢出登陆用户
     * @param userIds 用户ID列表
     */

    void kickOut(Set<Long> userIds);


    /**
     * token查询用户在线信息
     *
     * @param jwt token信息
     * @return onlineInfo
     */
    OnlineInfoDTO getOnlineInfo(DecodedJWT jwt);


    /**
     * 保存在线用户信息
     *
     * @param onlineInfoDTO 在线用户信息
     */
    void save(OnlineInfoDTO onlineInfoDTO);


    /**
     * 用户信息续约
     *
     * @param onlineInfoDTO 在线用户信息
     */
    void renew(OnlineInfoDTO onlineInfoDTO);

    /**
     * 删除在线用户信息
     *
     * @param onlineInfoDTO 在线用户信息
     */
    void remove(OnlineInfoDTO onlineInfoDTO);


    /**
     * 构建在线用户信息
     *
     * @param jwtId       jwtId
     * @param permissions 权限
     * @param adminVO     adminVo
     * @return online
     */
    OnlineInfoDTO buildOnlineAndSave(String jwtId, Set<String> permissions, AdminVO adminVO);


}
