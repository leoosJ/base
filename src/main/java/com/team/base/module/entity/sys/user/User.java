package com.team.base.module.entity.sys.user;

import com.team.base.base.annotation.Dic;
import com.team.base.base.entity.BaseEntity;
import com.team.base.common.constant.DictConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户实体
 */
@Data
public class User extends BaseEntity {

    @ApiModelProperty(value = "真实姓名", position = 1)
    private String realName;
    @ApiModelProperty(value = "用户名", position = 2)
    private String userName;
    @ApiModelProperty(value = "密码", position = 3)
    private String password;
    @ApiModelProperty(value = "性别", position = 4)
    @Dic(type = DictConstant.SEX)
    private String sex;
    @ApiModelProperty(value = "手机号", position = 5)
    private String mobile;
    @ApiModelProperty(value = "邮箱", position = 6)
    private String email;
    @ApiModelProperty(value = "头像", position = 7)
    private String userPhoto;
    @ApiModelProperty(value = "token", position = 8)
    private String token;
    @ApiModelProperty(value = "角色ID", position = 9)
    private String roleId;
    @ApiModelProperty(value = "角色名称", position = 9)
    private String roleName;

}
 
