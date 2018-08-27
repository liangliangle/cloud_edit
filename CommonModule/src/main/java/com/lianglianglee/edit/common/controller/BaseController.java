package com.lianglianglee.edit.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    /**
     * HTTP请求参数.
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.response.addHeader("Access-Control-Allow-Origin", "*");
        this.response.addHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        this.response.addHeader("Access-Control-Allow-Headers",
                "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        this.response.addHeader("Access-Control-Max-Age", "1728000");
    }
}
