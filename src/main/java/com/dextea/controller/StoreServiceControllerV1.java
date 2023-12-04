package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Order;
import com.dextea.server.StoreServiceServer;
import com.dextea.service.OrderService;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/service")
public class StoreServiceControllerV1 {
    @Autowired
    OrderService orderService;
    @Autowired
    StoreService storeService;

    //向店铺发送消息
    @PostMapping("/send")
    public JSONObject send(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        String sid = data.getString("sid");
        JSONObject sendData = data.getJSONObject("data");
        return StoreServiceServer.sendToStoreBySid(sid,sendData);
    }
    //获取今日门店订单
    @GetMapping("/order/today")
    public JSONObject getTodayOrder(@RequestParam("id") int id){
        return orderService.getTodayOrderV1(id);
    }
    //获取单笔订单详情
    @GetMapping("/order/detail")
    public JSONObject getOrderDetail(@RequestParam("id") int id){
        return orderService.getOrderDetailV1(id);
    }
    //更新制作进度
    @GetMapping("/order/update/state")
    public JSONObject updateOrderState(@RequestParam("sid") int sid,@RequestParam("oid") int oid,@RequestParam("state") String state){
        return orderService.updateOrderStateV1(sid,oid,state);
    }
    //获取门店营业状态
    @GetMapping("/store/state")
    public JSONObject getStoreState(@RequestParam("id") int id){
        return storeService.getStoreById(id);
    }
}
