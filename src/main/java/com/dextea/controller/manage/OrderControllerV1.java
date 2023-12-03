package com.dextea.controller.manage;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Order;
import com.dextea.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/order")
public class OrderControllerV1 {
    @Autowired
    OrderService orderService;
    //添加订单
    @PostMapping ("/add")
    public JSONObject addOrder(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return orderService.addOrderV1(data);
    }
    //获取所有订单
    @GetMapping("/list")
    public JSONObject list(){
        return orderService.getAllOrderV1();
    }
    //修改订单
    @PostMapping("/update")
    public JSONObject update(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return orderService.updateOrderV1(data);
    }
    //搜索订单
    @PostMapping("/search")
    public JSONObject search(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return orderService.searchOrderV1(data);
    }
}
