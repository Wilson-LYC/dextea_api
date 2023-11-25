package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Category;

public interface CategotyService {
    JSONObject addCategory(Category category);
    JSONObject getAllCategory();
    JSONObject getCateAsMultipleOption();
    JSONObject getCateAsSelectOption();

    JSONObject updateCategory(Category category);

    JSONObject deleteCategory(int id);
}
