package com.team.base.module.mapper.sys.menu;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.menu.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends CrudMapper<Menu> {

    List<Menu> findMenuTree();

}
