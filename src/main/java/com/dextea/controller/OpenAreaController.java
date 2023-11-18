package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.SettingService;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/openarea")
@CrossOrigin(origins = "*")
public class OpenAreaController {
    @Autowired
    StoreService storeService;

    @GetMapping("/get")
    public JSONObject all(){
        return storeService.getOpenArea();
    }
}
