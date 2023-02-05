package com.team.base.module.login.controller;

import com.team.base.base.entity.ResponseResult;
import com.team.base.common.constant.GlobalConstant;
import com.team.base.common.constant.RedisConstant;
import com.team.base.common.enumeration.LoginStatusEnum;
import com.team.base.common.utils.RedisUtil;
import com.team.base.module.login.service.TokenService;
import com.team.base.module.login.entity.Login;
import com.team.base.module.user.entity.User;
import com.team.base.module.user.service.UserService;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = { "用户登录" })
@RestController
public class LoginController {

    @Resource
    private TokenService tokenService;
    @Resource
    private UserService userService;

    @GetMapping(value = "/login")
    @ApiOperation(value = "登录")
    public ResponseResult<User> login(Login login, HttpServletResponse response, HttpServletRequest request) {
        ResponseResult<User> result = new ResponseResult<User>();

        // 获取登录失败次数
        Object value = RedisUtil.get(RedisConstant.LOGIN_FAIL_COUNT);

        // 登录失败次数>=3次，校验验证码
        if (value != null && (Integer)value >= 3) {
            // 验证码为空
            if (StringUtils.isEmpty(login.getVerifyCode())) {
                result.setStatus(LoginStatusEnum.C1002.getStatus());
                result.setMessage(LoginStatusEnum.C1002.getMessage());
                return result;
            } else {
                boolean verification = HappyCaptcha.verification(request, login.getVerifyCode(), true);
                if (verification) {
                    // 如果通过清除当前验证码验证
                    HappyCaptcha.remove(request);
                } else {
                    // 验证码错误
                    result.setStatus(LoginStatusEnum.C1001.getStatus());
                    result.setMessage(LoginStatusEnum.C1001.getMessage());
                    return result;
                }
            }
        }

        // 根据帐号获取用户信息
        User user = this.userService.findUserByAccount(login.getAccount());

        // 帐号或密码错误
        if (user == null || !login.getPassword().equals(user.getPassword())) {
            if (value == null) {
                // 设置第一次失败次数
                RedisUtil.set(RedisConstant.LOGIN_FAIL_COUNT, 1);
            } else {
                // 累加登录失败次数
                RedisUtil.set(RedisConstant.LOGIN_FAIL_COUNT, (Integer)value + 1);

                // 已经失败2次，第三次失败时返回需要验证码登录
                if ((Integer)value == 2) {
                    result.setStatus(LoginStatusEnum.C1004.getStatus());
                    result.setMessage(LoginStatusEnum.C1004.getMessage());
                    return result;
                }
            }

            result.setStatus(LoginStatusEnum.C1000.getStatus());
            result.setMessage(LoginStatusEnum.C1000.getMessage());
            return result;
        }

        // 生成token
        String token = this.tokenService.getToken(user);
        user.setToken(token);
        Cookie cookie = new Cookie(GlobalConstant.TOKEN, token);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader(GlobalConstant.AUTHORIZATION, token);

        // 登录成功，清空登录失败缓存
        if (value != null) {
            RedisUtil.remove(RedisConstant.LOGIN_FAIL_COUNT);
        }

        result.setData(user);
        return result;
    }

    @GetMapping("/generateCode")
    @ApiOperation(value = "生成验证码")
    public ResponseResult<Boolean> generateCode(HttpServletResponse response, HttpServletRequest request) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        HappyCaptcha.require(request, response)
                .style(CaptchaStyle.IMG)
                .build()
                .finish();
        result.setData(true);
        return result;
    }

    @PostMapping("/registry")
    @ApiOperation(value = "注册")
    public ResponseResult<User> registry(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        ResponseResult<User> result = this.userService.registry(user);
        if (result.getData() != null) {
            // 生成token
            String token = this.tokenService.getToken(result.getData());
            user.setToken(token);
            Cookie cookie = new Cookie(GlobalConstant.TOKEN, token);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.setHeader(GlobalConstant.AUTHORIZATION, token);
        }
        return result;
    }

    @PostMapping("/forgetPassword")
    @ApiOperation(value = "忘记密码")
    public ResponseResult<Boolean> forgetPassword(@RequestBody User user) {
        ResponseResult<Boolean> result = this.userService.forgetPassword(user);
        return result;
    }

}
