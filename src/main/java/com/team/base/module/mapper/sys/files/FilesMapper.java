package com.team.base.module.mapper.sys.files;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.files.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesMapper extends CrudMapper<Files> {

    Files getFileByMd5(String md5);

}
