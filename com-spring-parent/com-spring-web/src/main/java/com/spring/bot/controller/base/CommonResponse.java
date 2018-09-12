package com.spring.bot.controller.base;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

@Data
@ToString
public class CommonResponse<T> implements Serializable {
    private String errorCode;
    private String errorMsg;
    private T result;
    private Integer pageNum;
    private Integer pageSize;
    private Integer pages;
    private Long total;

    public CommonResponse(String errorCode, T result) {
        this.errorCode = errorCode;
        this.result = result;
    }

    public CommonResponse(String errorCode, String errorMsg, T result) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.result = result;
    }

    public CommonResponse(String errorCode, T result, Integer pageNum, Integer pageSize, Integer pages, Long total) {
        this.errorCode = errorCode;
        this.result = result;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
    }

    public CommonResponse(){}

}
