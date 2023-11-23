package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Category;
import com.dextea.service.CommodityService;
import com.dextea.service.StoreService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CommodityService commodityService;
    //新增品类
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Category category=new Category();
        category.setName(data.getString("name"));
        return commodityService.addCategory(category);
    }
    //获取所有品类
    @GetMapping("/get")
    public JSONObject all(){
        return commodityService.getAllCategory();
    }
}