package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    //新增分类
    @PostMapping("/add")
    public JSONObject addCategory(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return categoryService.addCategoryV1(data);
    }
    //获取所有分类
    @GetMapping("/all")
    public JSONObject getAllCategory(){
        return categoryService.getAllCategoryV1();
    }
    //删除品类
    @GetMapping("/delete")
    public JSONObject deleteCategory(@RequestParam("id") int id){
        return categoryService.deleteCategoryV1(id);
    }
    //更新品类
    @PostMapping("/update")
    public JSONObject updateCategory(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return categoryService.updateCategoryV1(data);
    }
    //获取品类下拉选项
    @GetMapping("/option/select")
    public JSONObject getCategorySelect(){
        return categoryService.getCateAsSelectOptionV1();
    }
    //获取品类多选选项
    @GetMapping("/option/checkbox")
    public JSONObject getCategoryCheckbox(){
        return categoryService.getCateAsCheckboxOptionV1();
    }
}
