package com.zzh.cloud;

import com.zzh.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/7 11:12
 */
@RestController
public class MovieCustController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie/{id}")
    public User findById(@PathVariable long id) {
        return restTemplate.getForObject("http://user-provider-reg/user/" + id, User.class);
    }

    @GetMapping("/choose")
    public String test() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider-reg");
        System.out.println("00000" + ":" + serviceInstance.getServiceId()
                + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
        ServiceInstance serviceInstance1 = this.loadBalancerClient.choose("user-provider-reg2");
        System.out.println("2222" + ":" + serviceInstance1.getServiceId()
                + ":" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort());
        return "choose successful";
    }
}
