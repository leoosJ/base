package com.team.base.module.entity.sys.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色下拉实体
 */
@Data
public class RoleSelect {

    @ApiModelProperty(value = "键", position = 1)
    private String value;
    @ApiModelProperty(value = "值", position = 2)
    private String label;

}
