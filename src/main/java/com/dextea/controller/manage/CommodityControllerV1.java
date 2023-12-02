package com.dextea.controller.manage;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/commodity")
public class CommodityControllerV1 {
    @Autowired
    CommodityService commodityService;
    //获取所有商品
    @GetMapping("/all")
    public JSONObject getAllCommodity(){
        return commodityService.getAllCommodityV1();
    }
    //搜索商品
    @PostMapping("/search")
    public JSONObject searchComm(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return commodityService.searchCommV1(data);
    }
    //删除商品
    @GetMapping("/delete")
    public JSONObject deleteComm(@RequestParam("id") int id){
        return commodityService.deleteCommV1(id);
    }
    //修改商品信息
    @PostMapping("/update/info")
    public JSONObject updateComm(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return commodityService.updateCommodityV1(data);
    }
    //修改销售状态
    @PostMapping("/update/state")
    public JSONObject updateCommState(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return commodityService.updateCommStateV1(data);
    }
    //新增商品
    @PostMapping("/add")
    public JSONObject addComm(@RequestBody JSONObject body){
        JSONObject data = body.getJSONObject("data");
        return commodityService.addCommodityV1(data);
    }
    //获取同一品类的商品
    @GetMapping("/samecate")
    public JSONObject getCommByCateId(@RequestParam("id") int id){
        return commodityService.getCommByCateIdV1(id);
    }
    //获取单个商品信息
    @GetMapping("/detail")
    public JSONObject getCommInfo(@RequestParam("id") int id) {
        return commodityService.getCommByIdV1(id);
    }
}
