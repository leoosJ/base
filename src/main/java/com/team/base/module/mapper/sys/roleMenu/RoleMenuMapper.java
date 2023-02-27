package com.team.base.module.mapper.sys.roleMenu;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.roleMenu.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends CrudMapper<RoleMenu> {

    int deleteByRoleId(String roleId);

    List<String> getMenuIdListByRoleId(String roleId);

}
