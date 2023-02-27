package com.team.base.base.entity;

import com.team.base.common.constant.GlobalConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回值封装
 *
 * @author JLP
 * @date 2022-12-17
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code = GlobalConstant.SUCCESS_CODE;
    private String msg = GlobalConstant.SUCCESS_MSG;
    private T data;

}
