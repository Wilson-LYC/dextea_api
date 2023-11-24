package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Commodity;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company/commodity")
@CrossOrigin(origins = "*")
public class CommodityController {
    @Autowired
    CommodityService commodityService;
    //获取所有商品（略）
    @GetMapping("/get")
    public JSONObject getComm(){
        return commodityService.getAllCommodity();
    }

    //新增商品
    @PostMapping("/add")
    public JSONObject addComm(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Commodity commodity=new Commodity();
        commodity.setName(data.getString("name"));
        commodity.setPrice(data.getDouble("price"));
        commodity.setState(data.getString("state"));
        return commodityService.addCommodity(commodity);
    }

    //获取商品列表和品类列表
    @GetMapping("/commcate")
    public JSONObject getCommCate(){
        return commodityService.getCommCate();
    }

    //获取单个商品
    @GetMapping("/info")
    public JSONObject getCommInfo(@RequestParam("id") int id){
        return commodityService.getCommInfo(id);
    }
}