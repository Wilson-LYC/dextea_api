//package com.dextea.controller;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.dextea.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/order")
//public class OrderController {
//    @Autowired
//    OrderService orderService;
//    //获取指定门店指定状态的订单
//    @GetMapping("/get/samestate")
//    public JSONObject getSameState(@RequestParam("sid") int sid, @RequestParam("state") String state){
//        return orderService.getSameState(sid,state);
//    }
//    //更新订单状态
//    @GetMapping("/update/state")
//    public JSONObject updateState(@RequestParam("id") int id,@RequestParam("state") String state){
//        return orderService.updateState(id,state);
//    }
//    //获取订单详情
//    @GetMapping("/detail")
//    public JSONObject getOrderDetail(@RequestParam("id") int id){
//        return orderService.getOrderDetail(id);
//    }
//}
