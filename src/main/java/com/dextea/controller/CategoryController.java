package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Category;
import com.dextea.service.CategotyService;
import com.dextea.service.CommodityService;
import com.dextea.service.StoreService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CommodityService commodityService;
    @Autowired
    CategotyService categotyService;
    //新增品类
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Category category=new Category();
        category.setName(data.getString("name"));
        return categotyService.addCategory(category);
    }
    //获取所有品类
    @GetMapping("/get/all")
    public JSONObject all(){
        return categotyService.getAllCategory();
    }
    //获取品类多选选项
    @GetMapping("/get/option/multiple")
    public JSONObject optionMultiple(){
        return categotyService.getCateAsMultipleOption();
    }
    //获取品类下拉选项
    @GetMapping("/get/option/select")
    public JSONObject optionSelect(){
        return categotyService.getCateAsSelectOption();
    }
    //更新品类
    @PostMapping("/update")
    public JSONObject update(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Category category=new Category();
        category.setId(data.getInteger("id"));
        category.setName(data.getString("name"));
        return categotyService.updateCategory(category);
    }

    //删除品类
    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("id") int id){
        return categotyService.deleteCategory(id);
    }
}