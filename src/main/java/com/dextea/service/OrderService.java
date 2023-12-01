package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Order;

public interface OrderService {
    JSONObject addOrder(Order order);

    String getOrderCode(int sid);//获取取餐码

    JSONObject getOrderList(int id);

    JSONObject getOrderDetailForCust(int id);

    JSONObject getStoreQueue(int id);

    JSONObject getSameState(int sid, String state);

    JSONObject updateState(int id, String state);

    JSONObject getOrderDetail(int id);
}
