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
     * commodity转换为json
     * @param commodity 商品
     * @return JSONObject
     */
    @Override
    public JSONObject toJson(Commodity commodity) {
        JSONObject res= JSONObject.from(commodity);
        if(commodity.getCustom()!=null && !commodity.getCustom().isEmpty())
            res.put("custom", JSONArray.parseArray(commodity.getCustom()));
        List<CommCate> commCateList=commCateMapper.getCateByCommId(commodity.getId());
        JSONArray cateList=new JSONArray();
        for(CommCate commCate:commCateList){
            String cateName=commCate.getCateName();
            cateList.add(cateName);
        }
        res.put("category",cateList);
        return res;
    }

    /**
     * commodityList转换为json
     * @param commodityList 商品列表
     * @return JSONArray
     */
    @Override
    public JSONArray toJson(List<Commodity> commodityList) {
        JSONArray res=new JSONArray();
        for(Commodity commodity:commodityList){
            JSONObject json=toJson(commodity);
            res.add(json);
        }
        return res;
    }

    /**
     * 获取商品列表(略)
     * @return JSONObject
     */
    @Override
    public JSONObject getCommBrief() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommBrief();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
        return res;
    }


    /**
     * 获取所有商品（详细）
     * @return JSONObject
     */
    @Override
    public JSONObject getAllCommodity() {
        JSONObject res=new JSONObject();
        List<Commodity> commodityList=commodityMapper.getAllCommodity();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("commodity",toJson(commodityList));
        res.put("data",data);
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
        data.put("commodity",toJson(commodity));
        res.put("data",data);
        return res;
    }

}
