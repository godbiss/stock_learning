package com.zhu.api_user;

import com.zhu.api_user.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiUserApplicationTests {

    @Autowired
    PermissionService permissionService;

    @Test
    void contextLoads() {
        System.out.println(permissionService.selectByUser(2));
    }

}
