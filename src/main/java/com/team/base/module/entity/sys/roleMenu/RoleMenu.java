package com.team.base.module.entity.sys.roleMenu;

import com.team.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色关联实体
 */
@Data
public class RoleMenu extends BaseEntity {

    @ApiModelProperty(value = "角色ID", position = 1)
    private String roleId;
    @ApiModelProperty(value = "菜单ID", position = 2)
    private String menuId;

}
