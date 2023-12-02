package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Category;

public interface CategotyService {
    //json转category
    Category toCategory(JSONObject data);
    //新增分类
    JSONObject addCategoryV1(JSONObject data);
    JSONObject addCategory(Category category);
    //获取所有分类
    JSONObject getAllCategoryV1();
    JSONObject getAllCategory();
    //删除品类
    JSONObject deleteCategoryV1(int id);
    JSONObject deleteCategory(int id);
    //更新品类
    JSONObject updateCategoryV1(JSONObject data);
    JSONObject updateCategory(Category category);
    //获取下拉选项
    JSONObject getCateAsSelectOptionV1();
    JSONObject getCateAsSelectOption();
    //获取多选选项
    JSONObject getCateAsCheckboxOptionV1();
    JSONObject getCateAsMultipleOption();



}
