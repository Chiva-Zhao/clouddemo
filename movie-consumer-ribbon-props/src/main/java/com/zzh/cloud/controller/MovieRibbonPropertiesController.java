package com.zzh.cloud.controller;

import com.zzh.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @version 1.0
 * @auther air
 * @date 2017/12/7 22:49
 **/
@RestController
public class MovieRibbonPropertiesController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User findUserById(@PathVariable Long id) {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider-reg");
        System.out.println(">>>>>" + " " + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
        return this.restTemplate.getForObject("http://user-provider-reg", User.class);
    }

    @GetMapping("/choose")
    public String test() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider-reg");
        System.out.println("00000" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
//        ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("user-provider-reg2");
//        System.out.println("22222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());
        return "choose successful";
    }
}
