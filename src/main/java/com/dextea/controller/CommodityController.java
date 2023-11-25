package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Commodity;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/commodity")
@CrossOrigin(origins = "*")
public class CommodityController {
    @Autowired
    CommodityService commodityService;
    //获取所有商品（简略）
    @GetMapping("/get/brief")
    public JSONObject getCommBrief(){
        return commodityService.getAllCommBrief();
    }
    //获取所有商品（完整）
    @GetMapping("/get/full")
    public JSONObject getComm(){
        return commodityService.getAllCommFull();
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

    //通过ID获取商品信息
    @GetMapping("/get/detail")
    public JSONObject getCommInfo(@RequestParam("id") int id){
        return commodityService.getCommInfo(id);
    }

    //更新商品信息
    @PostMapping("/update")
    public JSONObject updateComm(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Commodity commodity=new Commodity();
        commodity.setId(data.getInteger("id"));
        commodity.setName(data.getString("name"));
        commodity.setPrice(data.getDouble("price"));
        commodity.setState(data.getString("state"));
        if(data.getString("introduce")!=null && !data.getString("introduce").isEmpty())
            commodity.setIntroduce(data.getString("introduce"));
        if(data.getString("briefIntro")!=null && !data.getString("briefIntro").isEmpty())
            commodity.setBriefIntro(data.getString("briefIntro"));
        if(data.getString("custom")!=null && !data.getString("custom").isEmpty())
            commodity.setCustom(data.getString("custom"));
        JSONArray categoryArray=data.getJSONArray("category");
        return commodityService.updateCommodity(commodity,categoryArray);
    }
}
