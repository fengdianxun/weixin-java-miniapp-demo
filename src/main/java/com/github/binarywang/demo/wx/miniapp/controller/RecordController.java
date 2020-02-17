package com.github.binarywang.demo.wx.miniapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.github.binarywang.demo.wx.miniapp.WxMaDemoApplication;
import com.github.binarywang.demo.wx.miniapp.config.WxMaConfiguration;
import com.github.binarywang.demo.wx.miniapp.pojo.dto.BaseDTO;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  乘车记录
 *  Created by BinaryWang on 2017/6/16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/api/record")
public class RecordController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("add")
    public BaseDTO add(String openid,String wxmaqrocdeNo){
        BaseDTO baseDTO = new BaseDTO();
        Map<String,Integer> map = new HashMap<>();
        map.put("recordId",1);
        baseDTO.setData(map);
        return baseDTO;

    }

}
