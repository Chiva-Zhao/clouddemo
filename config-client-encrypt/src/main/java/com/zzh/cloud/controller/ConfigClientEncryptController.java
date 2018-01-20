package com.zzh.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-20 19:34
 **/
@RestController
public class ConfigClientEncryptController {
    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String getProfile() {
        return this.profile;
    }
}
