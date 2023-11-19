package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Store;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company/store")
@CrossOrigin(origins = "*")
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/get")
    public JSONObject all(){
        return storeService.getAllStore();
    }


}
