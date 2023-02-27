package com.team.base.module.entity.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录
 */
@Data
public class Login {

    @ApiModelProperty(value = "用户名", position = 1)
    private String userName;
    @ApiModelProperty(value = "密码", position = 2)
    private String password;
    @ApiModelProperty(value = "验证码", position = 3)
    private String verifyCode;

}
