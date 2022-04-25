package com.zhu.api_course.feign;

import com.zhu.api_course.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("api-user")
public interface UserService {

    @PostMapping("/user/updateUser")
    public Boolean updateUserMoney(@RequestBody User user);

    @GetMapping("/user/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id);
}
