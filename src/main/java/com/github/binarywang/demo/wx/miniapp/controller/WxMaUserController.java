package com.github.binarywang.demo.wx.miniapp.controller;

import com.github.binarywang.demo.wx.miniapp.WxMaDemoApplication;
import com.github.binarywang.demo.wx.miniapp.pojo.dto.BaseDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.github.binarywang.demo.wx.miniapp.config.WxMaConfiguration;
import com.github.binarywang.demo.wx.miniapp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/api/wx/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static Map<String,String> users = new HashMap<>();

    /**
     * 登陆接口
     */
    @RequestMapping("/login")
    public BaseDTO login(String code) {
        logger.debug("code:"+code);
        BaseDTO baseDTO = new BaseDTO();
        if (StringUtils.isBlank(code)) {
            baseDTO.setError_code(1);
            baseDTO.setMsg("empty jscode");
            return baseDTO;
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(WxMaDemoApplication.appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            Map<String, String> map = new HashMap<String, String>();
            map.put("openid", session.getOpenid());
            baseDTO.setData(map);
            users.put(session.getOpenid(),session.getSessionKey());
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            baseDTO.setError_code(1);
            baseDTO.setMsg(e.toString());
        }
        return baseDTO;
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @RequestMapping("/info")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @RequestMapping("/phone")
    public BaseDTO phone(String openid, String encryptedData, String iv) {
        logger.debug("openid:"+openid+" encryptedData:"+encryptedData+" iv:"+iv);
        final WxMaService wxService = WxMaConfiguration.getMaService(WxMaDemoApplication.appid);

//        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return "user check failed";
//        }
        String sessionKey = users.get(openid);

        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);

        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setData(phoneNoInfo);

        return baseDTO;
    }

}
