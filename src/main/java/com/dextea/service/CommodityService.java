package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Commodity;

import java.util.List;

public interface CommodityService {
    //commodity转换为json
    JSONObject toJson(Commodity commodity);
    //commodityList转换为json
    JSONArray toJson(List<Commodity> commodityList);
    //获取所有商品
    JSONObject getAllCommodityV1();
    //搜索商品
    JSONObject searchCommV1(JSONObject data);
    //删除商品
    JSONObject deleteCommV1(int id);
    //修改商品信息
    JSONObject updateCommodityV1(JSONObject data);
    //修改销售状态
    JSONObject updateCommStateV1(JSONObject data);
    //新增商品
    JSONObject addCommodityV1(JSONObject data);
    //获取同一品类的商品
    JSONObject getCommByCateIdV1(int id);
    //获取门店菜单
    JSONObject getMenuByStoreIdV1(int id);
    //上架商品
    JSONObject commOnsaleV1(JSONObject data);
    //下架商品
    JSONObject commOffsaleV1(JSONObject data);
    //获取单个商品信息
    JSONObject getCommByIdV1(int id);
    //旧版

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
