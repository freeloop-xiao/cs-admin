package com.cs.admin.system.auth.controller;

import com.cs.admin.common.controller.BaseController;
import com.cs.admin.system.auth.domain.dto.AuthDTO;
import com.cs.admin.system.auth.service.AuthService;
import com.cs.admin.system.auth.service.CaptchaService;
import com.cs.admin.system.auth.service.CheckHeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/1/26 21:39
 */

@Api(tags = "认证授权接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    private final CaptchaService captchaService;

    private final CheckHeaderService checkHeaderService;


    /**
     * 获取图形验证码
     *
     * @param authentication basic认证信息
     * @return captcha
     */
    @ApiOperation(value = "图形验证码", notes = "图形验证码")
    @GetMapping("/captcha")
    public ResponseEntity<?> captcha(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authentication) {
        checkHeaderService.headerAuthentication(authentication);
        return ResponseEntity.ok(captchaService.captcha());
    }


    /**
     * 登陆接口
     *
     * @param authDTO 字典类型新增
     * @return tokenVO
     */
    @ApiOperation(value = "登陆接口", notes = "登陆接口")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authentication,
                                   @Valid @RequestBody AuthDTO authDTO) {
        checkHeaderService.headerAuthentication(authentication);
        return ResponseEntity.ok(authService.login(authDTO));
    }

    /**
     * 退出接口
     *
     * @return tokenVO
     */
    @ApiOperation(value = "退出接口", notes = "退出接口")
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }

}
