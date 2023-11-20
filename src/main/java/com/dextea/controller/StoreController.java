package com.dextea.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Store;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/store")
@CrossOrigin(origins = "*")
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/get")
    public JSONObject all(){
        return storeService.getAllStore();
    }

    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Store store=new Store();
        store.setName(data.getString("name"));
        store.setArea(data.getString("area"));
        store.setAddress(data.getString("address"));
        store.setPhone(data.getString("phone"));
        store.setOpenTime(data.getString("openTime"));
        store.setOpenState(data.getString("openState"));
        return storeService.addStore(store);
    }

    @GetMapping("/info")
    public JSONObject info(@RequestParam("id") int id){
        return storeService.getStoreById(id);
    }

    //修改单个营业状态
    @PostMapping("/openstate/v1")
    public JSONObject openState1(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        int id=data.getInteger("storeId");
        String openState=data.getString("openState");
        return storeService.updateOpenState(id,openState);
    }

    //修改多个营业状态
    @PostMapping ("/openstate/v2")
    public JSONObject openState2(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        List<Integer> idList=data.getJSONArray("storeIdList").toJavaList(Integer.class);
        String openState=data.getString("openState");
        return storeService.updateOpenState(idList,openState);
    }
    //删除店铺
    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("id") int id){
        return storeService.deleteStoreById(id);
    }
}
