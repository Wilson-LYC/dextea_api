//package com.dextea.controller;
//
//import com.alibaba.fastjson2.JSONArray;
//import com.alibaba.fastjson2.JSONObject;
//import com.dextea.service.OpenAreaService;
//import com.dextea.service.StoreService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/openarea")
//public class OpenAreaController {
//    @Autowired
//    OpenAreaService openAreaService;
//
//    /**
//     * 获取所有开放区域
//     * @return 所有开放区域
//     */
//    @GetMapping("/get/all")
//    public JSONObject all(){
//        return openAreaService.getOpenArea();
//    }
//
//    /**
//     * 更新开放区域
//     * @return 更新结果
//     */
//    @PostMapping("/update")
//    public JSONObject update(@RequestBody JSONObject json){
//        JSONArray data=json.getJSONArray("data");
//        return openAreaService.updateOpenArea(data);
//    }
//
//    /**
//     * 获取营业区域选项
//     * @return 营业区域选项
//     */
//    @GetMapping("/get/option")
//    public JSONObject option(){
//        return openAreaService.getOpenAreaAsSelectOption();
//    }
//}