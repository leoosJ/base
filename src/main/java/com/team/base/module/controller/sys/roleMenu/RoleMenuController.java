package com.team.base.module.controller.sys.roleMenu;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.module.entity.sys.roleMenu.AssignmentMenu;
import com.team.base.module.service.sys.roleMenu.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = { "角色菜单管理" })
@RestController
@RequestMapping(value = "/auth/role/menu/")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @UserLoginToken
    @GetMapping(value = "getMenuIdListByRoleId/{roleId}")
    @ApiOperation(value = "获取角色ID查询分配菜单IDS")
    public ResponseResult<List<String>> getMenuIdListByRoleId(@PathVariable(value = "roleId") String roleId) {
        ResponseResult<List<String>> result = new ResponseResult<List<String>>();
        List<String> list = this.roleMenuService.getMenuIdListByRoleId(roleId);
        result.setData(list);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "assignmentMenu")
    @ApiOperation(value = "分配菜单")
    public ResponseResult<Boolean> assignmentMenu(@RequestBody AssignmentMenu assignmentMenu) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        Boolean assignmentFlag = this.roleMenuService.assignmentMenu(assignmentMenu);
        result.setData(assignmentFlag);
        return result;
    }

}
