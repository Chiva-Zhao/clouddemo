package com.zzh.cloud.feign;

import com.zzh.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zzh
 * @version 1.0
 * @date 2017/12/11 16:31
 */
@FeignClient("user-provider-reg")
public interface UserFeignClient {
    /**
     * 这里有两个坑需要注意：
     * <p>
     * 1、这里需要设置请求的方式为 RequestMapping 注解，用 GetMapping 注解是运行不成功的，即 GetMapping 不支持。
     * <p>
     * 2、注解 PathVariable 里面需要填充变量的名字，不然也是运行不成功的。
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    /**
     * 这里也有一个坑需要注意：
     * <p>
     * 如果入参是一个对象的话，那么这个方法在 feign 里面默认为 POST 方法，就算你写成 GET 方式也无济于事。
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user);
}
