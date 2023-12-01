package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Order;
import com.dextea.service.CommodityService;
import com.dextea.service.OpenAreaService;
import com.dextea.service.OrderService;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    OpenAreaService openAreaService;
    @Autowired
    StoreController storeController;
    @Autowired
    CommodityService commodityService;
    @Autowired
    OrderService orderService;
    @Autowired
    StoreService storeService;
    //获取营业区域
    @GetMapping("/openarea")
    public JSONObject getOpenArea(){
        return openAreaService.getOpenAreaForCustomer();
    }
    //获取门店
    @GetMapping("/store")
    public JSONObject getStore(@RequestParam("area") String area){
        return storeController.getStoreForCustomer(area);
    }
    //根据ID获取门店详情
    @GetMapping("/store/detail")
    public JSONObject getStoreDetail(@RequestParam("id") int id){
        return storeController.info(id);
    }
    //获取菜单
    @GetMapping("/menu")
    public JSONObject getMenu(@RequestParam("id") int id){
        return commodityService.getMenuByStoreId(id);
    }
    //创建订单
    @PostMapping("/order/add")
    public JSONObject addOrder(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Order order=new Order();
        order.setStoreId(data.getInteger("sid"));
        order.setCustId(data.getInteger("cid"));
        order.setState("2");
        order.setPrice(data.getDouble("price"));
        order.setNum(data.getInteger("num"));
        order.setPhone(data.getString("phone"));
        order.setNote(data.getString("note"));
        order.setCommodity(data.getString("commodity"));
        return orderService.addOrder(order);
    }
    //获取订单列表
    @GetMapping("/order/list")
    public JSONObject getOrderList(@RequestParam("id") int id){
        return orderService.getOrderList(id);
    }
    //获取订单详情
    @GetMapping("/order/detail")
    public JSONObject getOrderDetail(@RequestParam("id") int id){
        return orderService.getOrderDetailForCust(id);
    }
    //获取排队情况
    @GetMapping("/storequeue")
    public JSONObject getStoreQueue(@RequestParam("id") int id){
        return orderService.getStoreQueue(id);
    }
}
