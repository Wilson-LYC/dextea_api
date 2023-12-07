package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Store;
import java.util.List;

public interface StoreService {
    //store转换为json
    JSONObject toJson(Store store);
    //storeList转换为json
    JSONArray toJson(List<Store> storeList);
    //json转换为store
    Store toStore(JSONObject jsonObject);
    //storeList转换为下拉框选项
    JSONArray toSelectOption(List<Store> storeList);
    //新增门店
    JSONObject addStoreV1(JSONObject body);
    //获取所有门店
    JSONObject getAllStoreV1();
    //批量修改门店营业状态
    JSONObject multipleUpdateOpenStateV1(JSONObject data);
    //更新店铺信息
    JSONObject updateStoreV1(JSONObject data);
    //删除店铺
    JSONObject deleteStoreByIdV1(int id);
    //搜索店铺
    JSONObject searchStoreV1(JSONObject data);
    //获取店铺下拉选项
    JSONObject getStoreOptionSelectV1();
    //通过ID获取店铺
    JSONObject getStoreByIdV1(int id);
    JSONObject getStoreForCustomerV1(String area);
}
