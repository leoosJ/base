package com.team.base.module.mapper.sys.user;

import com.team.base.base.crud.CrudMapper;
import com.team.base.module.entity.sys.user.TransferUserResult;
import com.team.base.module.entity.sys.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends CrudMapper<User> {

    User findUserByUserName(String userName);

    int whetherUserNameExist(@Param("id") String id, @Param("userName") String userName);

    List<TransferUserResult> getUnAssignmentUserList(String roleId);

}
