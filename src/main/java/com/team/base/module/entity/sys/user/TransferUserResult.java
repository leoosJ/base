package com.team.base.module.entity.sys.user;

import com.team.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户实体
 */
@Data
public class TransferUserResult extends BaseEntity {

    @ApiModelProperty(value = "键", position = 1)
    private String key;
    @ApiModelProperty(value = "值", position = 2)
    private String label;
    @ApiModelProperty(value = "是否禁用", position = 3)
    private Boolean disabled;

}
 
