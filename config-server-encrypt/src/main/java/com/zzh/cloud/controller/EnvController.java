package com.zzh.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-1-20 12:52
 **/
@RestController
@RefreshScope
public class EnvController {

    @Autowired
    private Environment env;
    @GetMapping("/mypass")
    public String mypass() {
        return env.getProperty("my.password", "undefined");
    }
}
