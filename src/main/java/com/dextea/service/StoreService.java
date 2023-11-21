package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Store;

import java.util.List;


public interface StoreService {
    //获取营业区域
    public JSONObject getOpenArea();
    //更新营业区域
    JSONObject updateOpenArea(JSONArray openArea);
    //获取所有店铺
    JSONObject getAllStore();
    //获取店铺信息
    JSONObject getStoreById(int id);
    //添加店铺
    JSONObject addStore(Store store);

    JSONObject store2json(Store store);
    JSONArray storeList2json(List<Store> storeList);

    JSONObject updateOpenState(int id, String openState);

    JSONObject updateOpenState(List<Integer> idList, String openState);

    JSONObject deleteStoreById(int id);

    JSONObject getOpenAreaOption();

    JSONObject updateStore(Store store);
}
