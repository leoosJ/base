package com.team.base.module.service.sys.user;

import com.team.base.base.crud.CrudService;
import com.team.base.base.entity.ResponseResult;
import com.team.base.common.constant.GlobalConstant;
import com.team.base.common.enumeration.UserStatusEnum;
import com.team.base.common.utils.IdGenUtil;
import com.team.base.module.entity.sys.user.TransferUserResult;
import com.team.base.module.entity.sys.user.User;
import com.team.base.module.mapper.sys.user.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService extends CrudService<UserMapper, User> {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据帐号查询用户信息
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName) {
        return this.userMapper.findUserByUserName(userName);
    }

    /**
     * 用户名是都已被使用
     *
     * @param  id
     * @param userName
     * @return
     */
    public Boolean whetherUserNameExist(String id, String userName) {
        int count = this.userMapper.whetherUserNameExist(id, userName);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public ResponseResult<User> registry(User user) {
        ResponseResult<User> result = new ResponseResult<User>();

        // 根据帐号获取用户信息
        User dataUser = this.userMapper.findUserByUserName(user.getUserName());
        // 帐号已存在，无法注册
        if (dataUser != null) {
            result.setData(null);
            result.setCode(UserStatusEnum.C1000.getStatus());
            result.setMsg(UserStatusEnum.C1000.getMessage());
            return result;
        }

        // 注册用户
        user.setId(IdGenUtil.uuid());
        user.setCreateId(user.getId());
        user.setUpdateId(user.getId());
        user.setNewRecord(true);
        this.save(user);

        result.setData(user);
        return result;
    }

    /**
     * 修改密码
     *
     * @author JLP
     * @param user
     * @return\
     * @date 2022-12-21
     */
    public ResponseResult<Boolean> forgetPassword(User user) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();

        try {
            // 根据帐号获取用户信息
            User dataUser = this.userMapper.findUserByUserName(user.getUserName());
            if (dataUser == null) {
                // 帐号不存在
                result.setData(false);
                result.setCode(UserStatusEnum.C1001.getStatus());
                result.setMsg(UserStatusEnum.C1001.getMessage());
                return result;
            } else {
                // 更新帐号密码
                dataUser.setPassword(user.getPassword());
                this.save(dataUser);
                result.setData(true);
            }
        } catch (Exception e) {
            result.setData(false);
            result.setCode(GlobalConstant.FAIL_CODE);
            result.setMsg(GlobalConstant.FAIL_MESSAGE);
            return result;
        }

        return result;
    }

    /**
     * 查询未分配角色列表
     *
     * @author JLP
     * @return
     * @date 2023-02-20
     */
    public List<TransferUserResult> getUnAssignmentUserList(String roleId) {
        return this.userMapper.getUnAssignmentUserList(roleId);
    }

}
