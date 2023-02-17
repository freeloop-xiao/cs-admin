package com.cs.admin.common.dto;

import com.cs.admin.system.manager.domain.vo.SubDeptVO;
import com.cs.admin.system.manager.domain.vo.SubPostVO;
import com.cs.admin.system.manager.domain.vo.SubRoleVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 在线信息保存
 * 缓存保存结构  prefix + userId => OnlineInfoDTO
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/12 11:07
 */
@Data
public class OnlineInfoDTO {

    @ApiModelProperty(value = "token中jwtId")
    private String jwtId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "部门信息")
    private List<SubDeptVO> deptList;

    @ApiModelProperty(value = "岗位信息")
    private List<SubPostVO> postList;

    @ApiModelProperty(value = "角色列表")
    private List<SubRoleVO> roleList;

    @ApiModelProperty(value = "是否锁定 0开放 1锁定")
    private Boolean isLocked;

    @ApiModelProperty(value = "修改密码的时间")
    private LocalDateTime pwdResetTime;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "续约时间")
    private LocalDateTime renewTime;

    @ApiModelProperty(value = "权限集合")
    private Set<String> permissions;

}
