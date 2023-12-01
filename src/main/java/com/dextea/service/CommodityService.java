package com.dextea.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Category;
import com.dextea.pojo.CommCate;
import com.dextea.pojo.Commodity;
import com.dextea.pojo.Order;

import java.util.List;

public interface CommodityService {
    //commodity转换为json
    JSONObject toJson(Commodity commodity);
    //commodityList转换为json
    JSONArray toJson(List<Commodity> commodityList);

    //获取所有商品（简略）
    JSONObject getAllCommBrief();

    //获取所有商品（完整）
    JSONObject getAllCommFull();

    //新增商品
    JSONObject addCommodity(Commodity commodity);


    JSONObject getCommInfo(int id);

    JSONObject updateCommodity(Commodity commodity, JSONArray categoryArray);

    JSONObject searchComm(Commodity commodity, int cateId);

    JSONObject deleteComm(int id);

    JSONObject getCommByCateId(int cateId);

    JSONObject updateCommState(JSONArray idList, String state);

    JSONObject getStoreMenu(int id);

    JSONObject commOnsale(int cid, int sid);

    JSONObject commOffsale(int cid, int sid);

    JSONObject commOnsaleList(int sid, JSONArray cidList);

    JSONObject commOffsaleList(int sid, JSONArray cidList);

    JSONObject getMenuByStoreId(int id);

}
