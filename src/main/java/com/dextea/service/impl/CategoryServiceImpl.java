package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.CategoryMapper;
import com.dextea.pojo.Category;
import com.dextea.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * json转category
     * @param data
     * @return
     */
    @Override
    public Category toCategory(JSONObject data) {
        Category category=new Category();
        category.setName(data.getString("name"));
        return category;
    }

    /**
     * 新增品类v1
     * @param data 品类
     * @return JSONObject
     */
    @Override
    public JSONObject addCategoryV1(JSONObject data) {
        JSONObject res=new JSONObject();
        Category category=toCategory(data);
        try{
            categoryMapper.addCategory(category);
            res.put("code",200);
            res.put("msg","新增品类成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","新增品类失败");
        }
        return res;
    }

    /**
     * 获取所有品类v1
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCategoryV1() {
        JSONObject res=new JSONObject();
        try{
            List<Category> categoryList=categoryMapper.getAllCategory();
            res.put("code",200);
            res.put("msg","获取所有品类成功");
            JSONObject data=new JSONObject();
            data.put("category",categoryList);
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","获取所有品类失败");
        }
        return res;
    }

    /**
     * 删除品类v1
     * @param id 品类id
     * @return JSONObject
     */
    @Override
    public JSONObject deleteCategoryV1(int id) {
        JSONObject res=new JSONObject();
        try{
            categoryMapper.deleteCategory(id);
            res.put("code",200);
            res.put("msg","删除品类成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","删除品类失败");
        }
        return res;
    }

    /**
     * 更新品类v1
     * @param data 品类
     * @return JSONObject
     */
    @Override
    public JSONObject updateCategoryV1(JSONObject data) {
        JSONObject res=new JSONObject();
        Category category=toCategory(data);
        category.setId(data.getInteger("id"));
        try{
            categoryMapper.updateCategory(category);
            res.put("code",200);
            res.put("msg","更新品类成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","更新品类失败");
        }
        return res;
    }

    /**
     * 获取品类下拉选项v1
     * @return JSONObject
     */
    @Override
    public JSONObject getCateAsSelectOptionV1() {
        JSONObject res=new JSONObject();
        List<Category> categoryList=null;
        try{
            categoryList=categoryMapper.getAllCategory();
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
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
     * 获取多选选项v1
     * @return JSONObject
     */
    @Override
    public JSONObject getCateAsCheckboxOptionV1() {
        JSONObject res=new JSONObject();
        try{
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
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库异常");
        }
        return res;
    }
}
