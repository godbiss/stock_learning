package com.zhu.api_course.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("api-user")
public interface UserService {


}
