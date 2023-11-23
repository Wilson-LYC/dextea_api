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
        res.put("data",categoryList);
        return res;
    }

    /**
     * 获取所有商品（略）
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCommodity() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommodityBrief();
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
}
