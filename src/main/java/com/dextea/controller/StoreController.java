package com.dextea.controller;

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
}
