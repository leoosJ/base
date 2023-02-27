package com.team.base.module.controller.sys.role;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.base.page.Page;
import com.team.base.module.entity.sys.role.Role;
import com.team.base.module.entity.sys.role.RoleSelect;
import com.team.base.module.service.sys.role.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = { "角色管理" })
@RestController
@RequestMapping(value = "/auth/role/")
public class RoleController {

    @Resource
    private RoleService roleService;

    @UserLoginToken
    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "查询角色信息")
    public ResponseResult<Role> get(@PathVariable(value = "id") String id) {
        ResponseResult<Role> result = new ResponseResult<Role>();
        Role role = this.roleService.get(id);
        result.setData(role);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "list")
    @ApiOperation(value = "查询角色分页列表")
    public ResponseResult<Page<Role>> list(Role role, @RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        ResponseResult<Page<Role>> result = new ResponseResult<Page<Role>>();
        Page<Role> page = this.roleService.findPage(new Page<Role>(pageNum, pageSize), role);
        result.setData(page);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "getRoleSelectList")
    @ApiOperation(value = "查询角色下拉列表")
    public ResponseResult<List<RoleSelect>> getRoleSelectList() {
        ResponseResult<List<RoleSelect>> result = new ResponseResult<List<RoleSelect>>();
        List<RoleSelect> page = this.roleService.getRoleSelectList();
        result.setData(page);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "save")
    @ApiOperation(value = "保存角色")
    public ResponseResult<Boolean> save(@RequestBody Role role) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.roleService.save(role);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "物理删除角色")
    public ResponseResult<Boolean> delete(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.roleService.delete(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "deleteByLogic/{id}")
    @ApiOperation(value = "逻辑删除角色")
    public ResponseResult<Boolean> deleteByLogic(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.roleService.deleteByLogic(id);
        result.setData(true);
        return result;
    }

}
