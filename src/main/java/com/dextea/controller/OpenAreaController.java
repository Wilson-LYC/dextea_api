package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company/openarea")
@CrossOrigin(origins = "*")
public class OpenAreaController {
    @Autowired
    StoreService storeService;

    /**
     * 获取所有开放区域
     * @return 所有开放区域
     */
    @GetMapping("/get")
    public JSONObject all(){
        return storeService.getOpenArea();
    }

    /**
     * 更新开放区域
     * @param data 开放区域
     * @return 更新结果
     */
    @PostMapping("/update")
    //从body中获取JSON
    public JSONObject update(@RequestBody JSONObject data){
        JSONArray openArea=data.getJSONArray("newOpenArea");
        return storeService.updateOpenArea(openArea);
    }
}