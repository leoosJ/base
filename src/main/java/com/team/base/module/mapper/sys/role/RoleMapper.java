package com.team.base.module.mapper.sys.role;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.role.Role;
import com.team.base.module.entity.sys.role.RoleSelect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends CrudMapper<Role> {

    List<RoleSelect> getRoleSelectList();

}
