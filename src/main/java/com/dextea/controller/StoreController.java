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

    //添加店铺
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

    //删除店铺
    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("id") int id){
        return storeService.deleteStoreById(id);
    }

    //获取所有店铺
    @GetMapping("/get/all")
    public JSONObject all(){
        return storeService.getAllStore();
    }

    //获取店铺信息
    @GetMapping("/get/detail")
    public JSONObject info(@RequestParam("id") int id){
        return storeService.getStoreById(id);
    }
    //搜索店铺
    @PostMapping("/search")
    public JSONObject search(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Store store=new Store();
        if (data.getInteger("id")!=null){
            store.setId(data.getInteger("id"));
        }
        if(data.getString("name")!=null){
            store.setName(data.getString("name"));
        }
        if(data.getString("area")!=null){
            store.setArea(data.getString("area"));
        }
        if(data.getString("phone")!=null){
            store.setPhone(data.getString("phone"));
        }
        if(data.getString("openState")!=null){
            store.setOpenState(data.getString("openState"));
        }
        return storeService.searchStore(store);
    }

    //修改店铺信息
    @PostMapping("/update")
    public JSONObject update(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Store store=new Store();
        store.setId(data.getInteger("id"));
        store.setName(data.getString("name"));
        store.setArea(data.getString("area"));
        store.setAddress(data.getString("address"));
        store.setPhone(data.getString("phone"));
        store.setOpenTime(data.getString("openTime"));
        store.setOpenState(data.getString("openState"));
        return storeService.updateStore(store);
    }


    //修改单个营业状态
    @PostMapping("/update/openstate/v1")
    public JSONObject openState1(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        int id=data.getInteger("storeId");
        String openState=data.getString("openState");
        return storeService.updateOpenState(id,openState);
    }

    //修改多个营业状态
    @PostMapping ("/update/openstate/v2")
    public JSONObject openState2(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        List<Integer> idList=data.getJSONArray("storeIdList").toJavaList(Integer.class);
        String openState=data.getString("openState");
        return storeService.updateOpenState(idList,openState);
    }

    //获取店铺选项
    @GetMapping("/get/option/select")
    public JSONObject option(){
        return storeService.getStoreAsSelectOption();
    }
}
