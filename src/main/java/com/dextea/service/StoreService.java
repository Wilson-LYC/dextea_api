package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Store;

import java.util.List;


public interface StoreService {
    //store转换为json
    JSONObject toJson(Store store);

    //storeList转换为
    JSONArray toJson(List<Store> storeList);

    //storeList转换为下拉框选项
    JSONArray toSelectOption(List<Store> storeList);

    //添加店铺
    JSONObject addStore(Store store);

    //删除店铺
    JSONObject deleteStoreById(int id);

    //获取所有店铺
    JSONObject getAllStore();

    //通过ID获取店铺
    JSONObject getStoreById(int id);

    //搜索店铺
    JSONObject searchStore(Store store);

    //修改店铺信息
    JSONObject updateStore(Store store);
    //修改单个门店的营业状态
    JSONObject updateOpenState(int id, String openState);
    //修改多个门店的营业状态
    JSONObject updateOpenState(List<Integer> idList, String openState);
    //获取门店下拉选项
    JSONObject getStoreAsSelectOption();
}
