package com.team.base.module.controller.sys.roleUser;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.module.entity.sys.roleUser.AssignmentUser;
import com.team.base.module.service.sys.roleUser.RoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = { "角色用户管理" })
@RestController
@RequestMapping(value = "/auth/role/user/")
public class RoleUserController {

    @Resource
    private RoleUserService roleUserService;

    @UserLoginToken
    @GetMapping(value = "getUserIdListByRoleId/{roleId}")
    @ApiOperation(value = "根据角色ID查询已分配用户ID")
    public ResponseResult<List<String>> getUserIdListByRoleId(@PathVariable(value = "roleId") String roleId) {
        ResponseResult<List<String>> result = new ResponseResult<List<String>>();
        List<String> list = this.roleUserService.getUserIdListByRoleId(roleId);
        result.setData(list);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "assignmentUser")
    @ApiOperation(value = "分配用户")
    public ResponseResult<Boolean> assignmentUser(@RequestBody AssignmentUser assignmentUser) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        Boolean assignmentFlag = this.roleUserService.assignmentUser(assignmentUser);
        result.setData(assignmentFlag);
        return result;
    }

}
