package com.team.base.module.entity.sys.role;

import com.team.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色实体
 */
@Data
public class Role extends BaseEntity {

    @ApiModelProperty(value = "角色名称", position = 1)
    private String roleName;
    @ApiModelProperty(value = "角色代码", position = 2)
    private String roleCode;
    @ApiModelProperty(value = "角色描述", position = 3)
    private String roleDesc;

}
