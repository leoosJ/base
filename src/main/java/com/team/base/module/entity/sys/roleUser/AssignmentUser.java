package com.team.base.module.entity.sys.roleUser;

import com.team.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户角色关联实体
 */
@Data
public class AssignmentUser extends BaseEntity {

    @ApiModelProperty(value = "角色ID", position = 1)
    private String roleId;
    @ApiModelProperty(value = "用户ID集合", position = 2)
    private List<String> userIdList;

}
