package com.github.binarywang.demo.wx.miniapp.pojo.dto;

import lombok.Data;

@Data
public class BaseDTO<T> {

    private int error_code = 0;
    private String msg;
    private T data;

}
