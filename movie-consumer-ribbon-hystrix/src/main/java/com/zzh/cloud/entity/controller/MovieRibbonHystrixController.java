package com.zzh.cloud.entity.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzh.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozh
 * @date 2017/12/22
 */
@RestController
public class MovieRibbonHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/movie/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public User findById(@PathVariable Long id) {
        // http://localhost:7900/simple/
        // VIP：virtual IP
        // HAProxy Heartbeat
        return this.restTemplate.getForObject("http://user-provider-reg/user/" + id, User.class);
    }

    /**
     * 当 springms-provider-user 服务宕机或者不可用时，即请求超时后会调用此方法。
     *
     * @param id
     * @return
     */
    public User findByIdFallback(Long id) {
        User user = new User();
        user.setId(0L);
        return user;
    }
}
