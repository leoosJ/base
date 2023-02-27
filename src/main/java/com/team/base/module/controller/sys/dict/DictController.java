package com.team.base.module.controller.sys.dict;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.base.page.Page;
import com.team.base.common.enumeration.DictStatusEnum;
import com.team.base.module.entity.sys.dict.Dict;
import com.team.base.module.service.sys.dict.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = { "字典管理" })
@RestController
@RequestMapping(value = "/auth/dict/")
public class DictController {

    @Resource
    private DictService dictService;

    @UserLoginToken
    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "查询字典信息")
    public ResponseResult<Dict> get(@PathVariable(value = "id") String id) {
        ResponseResult<Dict> result = new ResponseResult<Dict>();
        Dict dict = this.dictService.get(id);
        result.setData(dict);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "list")
    @ApiOperation(value = "查询字典分页列表")
    public ResponseResult<Page<Dict>> list(Dict dict, @RequestParam(value = "pageNum", required = true) int pageNum, @RequestParam(value = "pageSize", required = true) int pageSize) {
        ResponseResult<Page<Dict>> result = new ResponseResult<Page<Dict>>();
        Page<Dict> page = this.dictService.findPage(new Page<Dict>(pageNum, pageSize), dict);
        result.setData(page);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "save")
    @ApiOperation(value = "保存字典")
    public ResponseResult<Boolean> save(@RequestBody Dict dict) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        Boolean dictExistFlag = this.dictService.whetherDictExist(dict);
        if (dictExistFlag) {
            result.setData(false);
            result.setCode(DictStatusEnum.C1000.getStatus());
            result.setMsg(DictStatusEnum.C1000.getMessage());
        } else {
            this.dictService.save(dict);
            result.setData(true);
        }
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "物理删除字典")
    public ResponseResult<Boolean> delete(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.dictService.delete(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "deleteByLogic/{id}")
    @ApiOperation(value = "逻辑删除字典")
    public ResponseResult<Boolean> deleteByLogic(@PathVariable(value = "id") String id) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.dictService.deleteByLogic(id);
        result.setData(true);
        return result;
    }

    @UserLoginToken
    @DeleteMapping(value = "deleteByIds")
    @ApiOperation(value = "批量删除字典")
    public ResponseResult<Boolean> deleteByIds(@RequestParam(value = "ids", required = true) String ids) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id: idArr) {
                this.dictService.deleteByLogic(id);
            }
        }
        result.setData(true);
        return result;
    }

}
