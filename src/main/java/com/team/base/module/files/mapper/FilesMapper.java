package com.team.base.module.files.mapper;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.files.entity.Files;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesMapper extends CrudMapper<Files> {

    Files getFileByMd5(String md5);

}
