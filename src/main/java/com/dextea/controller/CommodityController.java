package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Commodity;
import com.dextea.pojo.Store;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commodity")
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
        if(data.getString("img")!=null && !data.getString("img").isEmpty())
            commodity.setImg(data.getString("img"));
        JSONArray categoryArray=data.getJSONArray("category");
        return commodityService.updateCommodity(commodity,categoryArray);
    }

    //搜索商品
    @PostMapping("/search")
    public JSONObject searchComm(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Commodity commodity=new Commodity();
        if(data.getInteger("id")!=null)
            commodity.setId(data.getInteger("id"));
        if(data.getString("name")!=null)
            commodity.setName(data.getString("name"));
        if(data.getString("state")!=null)
            commodity.setState(data.getString("state"));
        int cateId;
        if(data.getInteger("cateId")!=null)
            cateId=data.getInteger("cateId");
        else
            cateId=0;
        return commodityService.searchComm(commodity,cateId);
    }

    //删除商品
    @GetMapping("/delete")
    public JSONObject deleteComm(@RequestParam("id") int id){
        return commodityService.deleteComm(id);
    }
    //通过cateId获取商品
    @GetMapping("/get/samecate")
    public JSONObject getCommByCateId(@RequestParam("cateId") int cateId){
        return commodityService.getCommByCateId(cateId);
    }
    //修改商品状态
    @PostMapping("/update/state")
    public JSONObject updateCommState(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        JSONArray idList=data.getJSONArray("idList");
        String state=data.getString("state");
        return commodityService.updateCommState(idList,state);
    }
    //获取商店菜单
    @GetMapping("/store")
    public JSONObject getStoreMenu(@RequestParam("id") int id){
        return commodityService.getStoreMenu(id);
    }

    //修改商品为在售
    @PostMapping("/store/onsale")
    public JSONObject updateCommOnSale(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        JSONArray cidList=data.getJSONArray("cid");
        int sid=data.getInteger("sid");
        return commodityService.commOnsaleList(sid,cidList);
    }
    //修改商品为下架
    @PostMapping("/store/offsale")
    public JSONObject updateCommOffSale(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        JSONArray cidList=data.getJSONArray("cid");
        int sid=data.getInteger("sid");
        return commodityService.commOffsaleList(sid,cidList);
    }

}
