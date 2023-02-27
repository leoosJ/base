package com.team.base.module.mapper.sys.dict;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.dict.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DictMapper extends CrudMapper<Dict> {

    /**
     * 根据字典类型和字典值获取字典标签
     * @param dictType
     * @param dictValue
     * @return
     */
    String getLabelByTypeAndValue(@Param(value = "dictType") String dictType, @Param(value = "dictValue") String dictValue);

    int whetherDictExist(Dict dict);

}
