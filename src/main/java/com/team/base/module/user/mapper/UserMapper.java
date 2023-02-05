package com.team.base.module.user.mapper;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends CrudMapper<User> {

    User findUserByAccount(String account);

}
