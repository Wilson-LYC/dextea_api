package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Category;
import com.dextea.pojo.CommCate;
import com.dextea.pojo.Commodity;

import java.util.List;

public interface CommodityService {
    //新增品类
    JSONObject addCategory(Category category);
    //获取所有品类
    JSONObject getAllCategory();

    //获取所有商品
    JSONObject getAllCommodity();
    //commodity转换为json
    JSONObject commodity2json(Commodity commodity);
    //commodityList转换为json
    JSONArray commodityList2json(List<Commodity> commodityList);
    //CommCateList转换为json
    JSONArray commCateList2json(List<CommCate> commCateList);
    //新增商品
    JSONObject addCommodity(Commodity commodity);
}
