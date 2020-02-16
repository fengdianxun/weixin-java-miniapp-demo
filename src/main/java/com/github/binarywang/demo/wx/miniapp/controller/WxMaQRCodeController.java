package com.github.binarywang.demo.wx.miniapp.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.github.binarywang.demo.wx.miniapp.WxMaDemoApplication;
import com.github.binarywang.demo.wx.miniapp.config.WxMaConfiguration;
import com.github.binarywang.demo.wx.miniapp.pojo.dto.BaseDTO;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *  小程序码生码接口
 *  Created by BinaryWang on 2017/6/16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Controller
@RequestMapping("/api/wxmaqrcode")
public class WxMaQRCodeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成小程序码.
     *
     * @return 小程序数据流.
     */
    @GetMapping("/create")
    public void create(String sence,HttpServletRequest request, HttpServletResponse response) throws WxErrorException, IOException {
        final WxMaService wxService = WxMaConfiguration.getMaService(WxMaDemoApplication.appid);
        File file = wxService.getQrcodeService().createWxaCodeUnlimit(sence,null);
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));
        response.getOutputStream().write(bytes);
    }

    @RequestMapping("query")
    public BaseDTO query(String openid,String wxmaqrocdeNo){
        BaseDTO baseDTO = new BaseDTO();
        Map<String,String> map = new HashMap<>();
        map.put("wxmaqrocdeNo",wxmaqrocdeNo);
        map.put("cardNo","津A88888");
        map.put("activer","1");
        baseDTO.setData(map);
        return baseDTO;

    }
    @RequestMapping("bind")
    public BaseDTO bind(String openid,String wxmaqrocdeNo,String cardNo){
        BaseDTO baseDTO = new BaseDTO();
        Map<String,String> map = new HashMap<>();
        map.put("wxmaqrocdeNo",wxmaqrocdeNo);
        baseDTO.setData(map);
        return baseDTO;

    }

}
