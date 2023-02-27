package com.team.base.module.mapper.sys.roleUser;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.roleUser.RoleUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleUserMapper extends CrudMapper<RoleUser> {

    int deleteByRoleId(String roleId);

    RoleUser getRoleUserByUserId(String userId);

    List<String> getUserIdListByRoleId(String roleId);

}
