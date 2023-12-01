package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    HttpServletRequest request;
    @PostMapping("/staff")
    public JSONObject loginStaff(@RequestBody JSONObject json){
        //获取IP
        String ip=request.getRemoteAddr();
        JSONObject data=json.getJSONObject("data");
        return loginService.loginStaff(data,ip);
    }
    @GetMapping("/staffout")
    public JSONObject logoutStaff(@RequestHeader("Authorization") String token){
        return loginService.logoutStaff(token);
    }

    @PostMapping ("/customer")
    public JSONObject loginCustomer(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        String code=data.getString("code");
        return loginService.customerLogin(code);
    }

}
