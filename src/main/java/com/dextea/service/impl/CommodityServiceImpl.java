package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.CategoryMapper;
import com.dextea.mapper.CommCateMapper;
import com.dextea.mapper.CommodityMapper;
import com.dextea.pojo.Category;
import com.dextea.pojo.CommCate;
import com.dextea.pojo.Commodity;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CommCateMapper commCateMapper;
    @Autowired
    CommodityMapper commodityMapper;
    /**
     * 新增品类
     * @param category
     * @return
     */
    @Override
    public JSONObject addCategory(Category category) {
        JSONObject res=new JSONObject();
        int result=categoryMapper.addCategory(category);
        if(result==1){
            res.put("code",200);
            res.put("msg","新增品类成功");
        }else{
            res.put("code",500);
            res.put("msg","新增品类失败");
        }
        return res;
    }

    /**
     * 获取所有品类
     * @return
     */
    @Override
    public JSONObject getAllCategory() {
        JSONObject res=new JSONObject();
        res.put("code",200);
        res.put("msg","成功");
        List<Category> categoryList=categoryMapper.getAllCategory();
        JSONArray categoryListJson=new JSONArray();
        for(Category category:categoryList){
            JSONObject json=new JSONObject();
            json.put("id",category.getId());
            json.put("name",category.getName());
            categoryListJson.add(json);
        }
        JSONObject data=new JSONObject();
        data.put("category",categoryListJson);
        res.put("data",data);
        return res;
    }

    /**
     * 获取所有商品（略）
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCommodity() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommodity();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",commodityList2json(commodityList));
        res.put("data",data);
        return res;
    }

    /**
     * commodity转换为json
     * @return JSONObject
     */
    @Override
    public JSONObject commodity2json(Commodity commodity) {
        JSONObject res= JSONObject.from(commodity);
        JSONArray custom=JSONArray.parseArray(commodity.getCustom());
        res.put("custom",custom);
        List<CommCate> commCateList=commCateMapper.getCateByCommId(commodity.getId());
        JSONArray cateList=commCateList2json(commCateList);
        res.put("category",cateList);
        return res;
    }

    /**
     * commodityList转换为json
     * @return
     */
    @Override
    public JSONArray commodityList2json(List<Commodity> commodityList) {
        JSONArray res=new JSONArray();
        for(Commodity commodity:commodityList){
            JSONObject json=commodity2json(commodity);
            res.add(json);
        }
        return res;
    }

    /**
     * CommCateList转换为json
     * @param commCateList
     * @return
     */
    @Override
    public JSONArray commCateList2json(List<CommCate> commCateList) {
        JSONArray res=new JSONArray();
        for(CommCate commCate:commCateList){
            String cateName=categoryMapper.getCateNameById(commCate.getCateId());
            res.add(cateName);
        }
        return res;
    }

    /**
     * 新增商品
     * @param commodity
     * @return
     */
    @Override
    public JSONObject addCommodity(Commodity commodity) {
        JSONObject res=new JSONObject();
        int result=commodityMapper.addCommodity(commodity);
        if(result==1){
            res.put("code",200);
            res.put("msg","新增商品成功");
        }else{
            res.put("code",500);
            res.put("msg","新增商品失败");
        }
        return res;
    }

    /**
     * 获取商品列表和品类列表
     * @return
     */
    @Override
    public JSONObject getCommCate() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommodity();
        List<Category> categoryList=categoryMapper.getAllCategory();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        JSONArray commArray=new JSONArray();
        for(Commodity commodity:commodityList){
            JSONObject commJson=commodity2json(commodity);
            commJson.remove("custom");
            commJson.remove("introduce");
            commJson.remove("briefIntro");
            commJson.remove("img");
            commArray.add(commJson);
        }
        data.put("commodity",commArray);
        data.put("category",categoryList2option(categoryList));
        res.put("data",data);
        return res;
    }

    /**
     * category转换为option
     * @param categoryList
     * @return JSONArray
     */
    @Override
    public JSONArray categoryList2option(List<Category> categoryList) {
        JSONArray res=new JSONArray();
        for(Category category:categoryList){
            JSONObject json=new JSONObject();
            json.put("value",category.getId());
            json.put("label",category.getName());
            res.add(json);
        }
        return res;
    }

    /**
     * 获取单个商品
     * @param id
     * @return
     */
    @Override
    public JSONObject getCommInfo(int id) {
        JSONObject res=new JSONObject();
        Commodity commodity=commodityMapper.getCommById(id);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",commodity2json(commodity));
        res.put("data",data);
        return res;
    }

    /**
     * 获取品类多选选项
     * @return
     */
    @Override
    public JSONObject getCateOptionMultiple() {
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
}
