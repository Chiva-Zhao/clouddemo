package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServicePath}")
    private String userServicePath;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject(this.userServicePath + id, User.class);
    }

    /**
     * 添加给 sidecar 微服务做测试用的代码。
     *
     * @return
     */
    @GetMapping("/sidecar")
    public String sidecar() {
        return this.restTemplate.getForObject("http://localhost:8200/sidecar", String.class);
    }

    /**
     * 添加给 sidecar 微服务做测试用的代码。
     *
     * @return
     */
    @GetMapping("/sidecar/health.json")
    public String sidecarHealth() {
        return this.restTemplate.getForObject("http://localhost:8200/sidecar/health.json", String.class);
    }
}
