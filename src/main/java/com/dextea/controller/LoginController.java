package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/staff")
    public JSONObject loginStaff(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        String account=data.getString("account");
        String password=data.getString("password");
        return loginService.loginStaff(account,password);
    }
}
