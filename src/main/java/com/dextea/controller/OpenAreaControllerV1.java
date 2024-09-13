package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.OpenAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/openarea")
public class OpenAreaControllerV1 {
    @Autowired
    OpenAreaService openAreaService;
    //更新营业区域
    @PostMapping("/update")
    public JSONObject updateOpenArea(@RequestBody JSONObject body) {
        JSONArray data= body.getJSONArray("data");
        return openAreaService.updateOpenAreaV1(data);
    }
    //获取所有营业区域
    @GetMapping("/all")
    public JSONObject getOpenArea() {
        return openAreaService.getOpenAreaV1();
    }
    //获取营业区域下拉选项
    @GetMapping("/option")
    public JSONObject getOpenAreaAsSelectOption() {
        return openAreaService.getOpenAreaAsSelectOptionV1();
    }
}
