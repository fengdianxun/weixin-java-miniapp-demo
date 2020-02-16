package com.github.binarywang.demo.wx.miniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@SpringBootApplication
public class WxMaDemoApplication {
    public static final String appid = "wx804bacf810756b8d";

    public static void main(String[] args) {
        SpringApplication.run(WxMaDemoApplication.class, args);
    }
}
