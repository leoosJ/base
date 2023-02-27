package com.team.base.module.entity.sys.roleUser;

import com.team.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色关联实体
 */
@Data
public class RoleUser extends BaseEntity {

    @ApiModelProperty(value = "角色ID", position = 1)
    private String roleId;
    @ApiModelProperty(value = "用户ID", position = 2)
    private String userId;

}
