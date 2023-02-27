package com.team.base.common.enumeration;

/**
 * 用户登录枚举
 *
 * @author JLP
 * @date 2022-12-19
 */
public enum DictStatusEnum {

    C1000("字典已存在", 1000);

    private String message;
    private Integer status;

    DictStatusEnum(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status + "_" + this.message;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getStatus() {
        return this.status;
    }

}
