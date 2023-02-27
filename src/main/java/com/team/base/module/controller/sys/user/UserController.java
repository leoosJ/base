package com.team.base.module.controller.sys.user;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.base.page.Page;
import com.team.base.common.enumeration.UserStatusEnum;
import com.team.base.module.entity.sys.user.TransferUserResult;
import com.team.base.module.entity.sys.user.User;
import com.team.base.module.service.sys.roleUser.RoleUserService;
import com.team.base.module.service.sys.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = { "用户管理" })
@RestController
@RequestMapping(value = "/auth/user/")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleUserService roleUserService;

    @UserLoginToken
    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "查询用户信息")
    public ResponseResult<User> get(@PathVariable(value = "id") String id) {
        ResponseResult<User> result = new ResponseResult<User>();
        User user = this.userService.get(id);
        result.setData(user);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "list")
    @ApiOperation(value = "查询用户分页列表")
    public ResponseResult<Page<User>> list(User user, @RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        ResponseResult<Page<User>> result = new ResponseResult<Page<User>>();
        Page<User> page = this.userService.findPage(new Page<User>(pageNum, pageSize), user);
        result.setData(page);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "getUnAssignmentUserList/{roleId}")
    @ApiOperation(value = "根据角色ID查询未分配角色用户列表")
    public ResponseResult<List<TransferUserResult>> getUnAssignmentUserList(@PathVariable(value = "roleId") String roleId) {
        ResponseResult<List<TransferUserResult>> result = new ResponseResult<List<TransferUserResult>>();
        List<TransferUserResult> list = this.userService.getUnAssignmentUserList(roleId);
        result.setData(list);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "save")
    @ApiOperation(value = "保存用户")
    public ResponseResult<Boolean> save(@RequestBody User user) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        Boolean userExistFlag = this.userService.whetherUserNameExist(user.getId(), user.getUserName());
        if (userExistFlag) {
            result.setData(false);
            result.setCode(UserStatusEnum.C1000.getStatus());
            result.setMsg(UserStatusEnum.C1000.getMessage());
        } else {
            this.userService.save(user);

            // 保存角色
            this.roleUserService.saveRoleUser(user.getRoleId(), user.getId());

            result.setData(true);
        }
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "物理删除用户")
    public ResponseResult<Boolean> delete(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.userService.delete(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "deleteByLogic/{id}")
    @ApiOperation(value = "逻辑删除用户")
    public ResponseResult<Boolean> deleteByLogic(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.userService.deleteByLogic(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "批量删除用户")
    public ResponseResult<Boolean> deleteByIds(@RequestParam(value = "ids", required = true) String ids) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id: idArr) {
                this.userService.deleteByLogic(id);
            }
        }
        result.setData(true);
        return result;
    }

}
