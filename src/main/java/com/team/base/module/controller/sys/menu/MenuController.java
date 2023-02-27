package com.team.base.module.controller.sys.menu;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.base.page.Page;
import com.team.base.module.entity.sys.menu.Menu;
import com.team.base.module.service.sys.menu.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = { "菜单管理" })
@RestController
@RequestMapping(value = "/auth/menu/")
public class MenuController {

    @Resource
    private MenuService menuService;

    @UserLoginToken
    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "查询菜单信息")
    public ResponseResult<Menu> get(@PathVariable(value = "id") String id) {
        ResponseResult<Menu> result = new ResponseResult<Menu>();
        Menu menu = this.menuService.get(id);
        result.setData(menu);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "list")
    @ApiOperation(value = "查询菜单分页列表")
    public ResponseResult<Page<Menu>> list(Menu menu, @RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        ResponseResult<Page<Menu>> result = new ResponseResult<Page<Menu>>();
        Page<Menu> page = this.menuService.findPage(new Page<Menu>(pageNum, pageSize), menu);
        result.setData(page);
        return result;
    }

    @UserLoginToken
    @GetMapping("findMenuTree")
    @ApiOperation(value = "获取菜单树形列表")
    public ResponseResult<List<Menu>> findMenuTree() {
        ResponseResult<List<Menu>> result = new ResponseResult<List<Menu>>();
        List<Menu> list = this.menuService.findMenuTree();
        result.setData(list);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "save")
    @ApiOperation(value = "保存菜单")
    public ResponseResult<Boolean> save(@RequestBody Menu menu) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.menuService.save(menu);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "物理删除菜单")
    public ResponseResult<Boolean> delete(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.menuService.delete(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "deleteByLogic/{id}")
    @ApiOperation(value = "逻辑删除菜单")
    public ResponseResult<Boolean> deleteByLogic(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.menuService.deleteByLogic(id);
        result.setData(true);
        return result;
    }

}
