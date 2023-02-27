package com.team.base.module.entity.sys.dict;

import com.team.base.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典实体
 */
@Data
public class Dict extends BaseEntity {

    @ApiModelProperty(value = "字典类型", position = 1)
    private String dictType;
    @ApiModelProperty(value = "字典值", position = 2)
    private String dictValue;
    @ApiModelProperty(value = "字典标签", position = 3)
    private String dictLabel;
    @ApiModelProperty(value = "字典描述", position = 4)
    private String dictDesc;

}
