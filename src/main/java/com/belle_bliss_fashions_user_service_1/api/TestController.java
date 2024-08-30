package com.belle_bliss_fashions_user_service_1.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/belle-bliss-user-service/api/v1/test")
public class TestController {

    @GetMapping
    public String test(){
        return "User service is running";
    }
}
