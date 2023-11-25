package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.CategoryMapper;
import com.dextea.pojo.Category;
import com.dextea.service.CategotyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategotyService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 新增品类
     * @param category 品类
     * @return JSONObject
     */
    @Override
    public JSONObject addCategory(Category category) {
        JSONObject res=new JSONObject();
        int flag=categoryMapper.addCategory(category);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 获取所有品类
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCategory() {
        JSONObject res=new JSONObject();
        List<Category> categoryList=categoryMapper.getAllCategory();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("category",categoryList);
        res.put("data",data);
        return res;
    }

    /**
     * 获取品类多选选项
     * @return JSONObject
     */
    @Override
    public JSONObject getCateAsMultipleOption() {
        JSONObject res=new JSONObject();
        List<Category> categoryList=categoryMapper.getAllCategory();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        JSONArray options=new JSONArray();
        for(Category category:categoryList){
            options.add(category.getName());
        }
        data.put("options",options);
        res.put("data",data);
        return res;
    }

    @Override
    public JSONObject getCateAsSelectOption() {
        JSONObject res=new JSONObject();
        List<Category> categoryList=categoryMapper.getAllCategory();
        JSONObject data=new JSONObject();
        JSONArray cate=new JSONArray();
        for(Category category:categoryList){
            JSONObject json=new JSONObject();
            json.put("value",category.getId());
            json.put("label",category.getName());
            cate.add(json);
        }
        res.put("code",200);
        res.put("msg","成功");
        data.put("category",cate);
        res.put("data",data);
        return res;
    }

    /**
     * 更新品类
     * @param category 品类
     * @return JSONObject
     */
    @Override
    public JSONObject updateCategory(Category category) {
        JSONObject res=new JSONObject();
        int flag=categoryMapper.updateCategory(category);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 删除品类
     * @param id 品类id
     * @return JSONObject
     */
    @Override
    public JSONObject deleteCategory(int id) {
        JSONObject res=new JSONObject();
        int flag=categoryMapper.deleteCategory(id);
        if(flag==0){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }
}
