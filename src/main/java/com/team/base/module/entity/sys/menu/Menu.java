package com.team.base.module.entity.sys.menu;

import com.team.base.base.annotation.Dic;
import com.team.base.base.entity.BaseEntity;
import com.team.base.common.constant.DictConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单实体
 */
@Data
public class Menu extends BaseEntity {

    @ApiModelProperty(value = "菜单名称", position = 1)
    private String menuName;
    @ApiModelProperty(value = "上级菜单", position = 2)
    private String parentId;
    @ApiModelProperty(value = "所属类型", position = 3)
    @Dic(type = DictConstant.MENU_TYPE)
    private String menuType;
    @ApiModelProperty(value = "权限标识", position = 4)
    private String permissionCode;
    @ApiModelProperty(value = "图标", position = 5)
    private String icon;
    @ApiModelProperty(value = "路由", position = 6)
    private String router;
    @ApiModelProperty(value = "排序", position = 7)
    private Integer sort;
    @ApiModelProperty(value = "是否显示 0否 1是", position = 8)
    @Dic(type = DictConstant.WHETHER)
    private String showFlag;
    @ApiModelProperty(value = "子节点", position = 9)
    private List<Menu> children;

}
