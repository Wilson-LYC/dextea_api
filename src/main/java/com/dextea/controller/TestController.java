package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/link")
    public String logoutStaff(){
        return "success";
    }
}
