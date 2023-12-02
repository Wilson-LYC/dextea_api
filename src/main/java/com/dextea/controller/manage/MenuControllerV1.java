package com.dextea.controller.manage;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/menu")
public class MenuControllerV1 {
    @Autowired
    CommodityService commodityService;
    //获取菜单列表
    @GetMapping("/get")
    public JSONObject getMenuList(@RequestParam("id") int id) {
        return commodityService.getMenuByStoreIdV1(id);
    }
    //上架商品
    @PostMapping("/onsale")
    public JSONObject getOnsaleComm(@RequestBody JSONObject body) {
        JSONObject data = body.getJSONObject("data");
        return commodityService.commOnsaleV1(data);
    }
    //下架商品
    @PostMapping("/offsale")
    public JSONObject getOffsaleComm(@RequestBody JSONObject body) {
        JSONObject data = body.getJSONObject("data");
        return commodityService.commOffsaleV1(data);
    }
}
