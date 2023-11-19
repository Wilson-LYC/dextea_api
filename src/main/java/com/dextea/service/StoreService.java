package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;


public interface StoreService {
    //获取营业区域
    public JSONObject getOpenArea();
    //更新营业区域
    JSONObject updateOpenArea(JSONArray openArea);
    //获取所有店铺
    JSONObject getAllStore();
}
