package com.team.base.module.files.controller;

import com.team.base.base.entity.ResponseResult;
import com.team.base.base.interceptor.UserLoginToken;
import com.team.base.module.files.entity.Files;
import com.team.base.module.files.service.FilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = { "文件管理" })
@RestController
@RequestMapping(value = "/file")
public class FilesController {

    @Resource
    private FilesService filesService;

    @UserLoginToken
    @PostMapping(value = "/get/{fileId}")
    @ApiOperation(value = "获取文件")
    public ResponseResult<Files> get(@PathVariable(value = "fileId") String fileId) {
        ResponseResult<Files> result = new ResponseResult<Files>();
        Files files = this.filesService.get(fileId);
        result.setData(files);
        return result;
    }

    @UserLoginToken
    @PostMapping(value = "/upload")
    @ApiOperation(value = "文件上传")
    public ResponseResult<Files> upload(MultipartFile file) throws IOException {
        ResponseResult<Files> result = new ResponseResult<Files>();
        Files files = this.filesService.upload(file);
        result.setData(files);
        return result;
    }

    @UserLoginToken
    @GetMapping(value = "/download/{fileId}")
    @ApiOperation(value = "文件下载")
    public void download(@PathVariable(value = "fileId") String fileId, HttpServletResponse response) throws IOException {
        this.filesService.download(fileId, response);
    }

    @UserLoginToken
    @DeleteMapping(value = "/delete/{fileId}")
    @ApiOperation(value = "文件删除")
    public ResponseResult<Boolean> delete(@PathVariable(value = "fileId") String fileId) {
        ResponseResult<Boolean> result = new ResponseResult<Boolean>();
        this.filesService.delete(fileId);
        result.setData(true);
        return result;
    }

}
