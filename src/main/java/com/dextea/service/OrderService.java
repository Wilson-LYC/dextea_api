package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrderService {
    //转为json
    JSONObject toJson(Order order);
    //列表转为json
    JSONArray toJson(List<Order> orderList);
    //获取所有订单
    JSONObject getAllOrderV1();
    //添加订单
    JSONObject addOrderV1(JSONObject data);
    //修改订单
    JSONObject updateOrderV1(JSONObject data);
    //搜索订单
    JSONObject searchOrderV1(JSONObject json);
    //获取今日门店订单
    JSONObject getTodayOrderV1(int id);
    //获取单笔订单详情
    JSONObject getOrderDetailV1(int id);
    //更新制作进度
    JSONObject updateOrderStateV1(int sid,int oid,String state);
    //旧版
    JSONObject addOrder(Order order);

    String getOrderCode(int sid);//获取取餐码

    JSONObject getOrderList(int id);

    JSONObject getOrderDetailForCust(int id);

    JSONObject getStoreQueue(int id);

    JSONObject getSameState(int sid, String state);

    JSONObject updateState(int id, String state);

    JSONObject getOrderDetail(int id);



}
