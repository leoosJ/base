package com.team.base.module.service.sys.dict;

import com.team.base.base.crud.CrudService;
import com.team.base.module.entity.sys.dict.Dict;
import com.team.base.module.mapper.sys.dict.DictMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DictService extends CrudService<DictMapper, Dict> {

    @Resource
    private DictMapper dictMapper;

    /**
     * 根据字典类型和字典值获取字典标签
     * @param dictType
     * @param dictValue
     * @return
     */
    public String getLabelByTypeAndValue(String dictType, String dictValue) {
        return this.dictMapper.getLabelByTypeAndValue(dictType, dictValue);
    }

    /**
     * 字典值是否已存在
     *
     * @param  dict
     * @return
     */
    public Boolean whetherDictExist(Dict dict) {
        int count = this.dictMapper.whetherDictExist(dict);
        if (count > 0) {
            return true;
        }
        return false;
    }

}
