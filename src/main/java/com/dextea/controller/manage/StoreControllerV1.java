package com.dextea.controller.manage;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/store")
public class StoreControllerV1 {
    @Autowired
    StoreService storeService;
    //新增门店
    @PostMapping("/add")
    public JSONObject addStore(@RequestBody JSONObject body) {
        JSONObject data= body.getJSONObject("data");
        return storeService.addStoreV1(data);
    }
    //获取所有门店
    @GetMapping("/all")
    public JSONObject getAllStore() {
        return storeService.getAllStoreV1();
    }
    //批量修改门店营业状态
    @PostMapping("/update/openstate/multiple")
    public JSONObject updateOpenStateMultiple(@RequestBody JSONObject body) {
        JSONObject data= body.getJSONObject("data");
        return storeService.multipleUpdateOpenStateV1(data);
    }
    //更新店铺信息
    @PostMapping("/update/info")
    public JSONObject updateStoreInfo(@RequestBody JSONObject body) {
        JSONObject data= body.getJSONObject("data");
        return storeService.updateStoreV1(data);
    }
    //删除店铺
    @GetMapping("/delete")
    public JSONObject deleteStoreById(@RequestParam int id) {
        return storeService.deleteStoreByIdV1(id);
    }
    //搜索店铺
    @PostMapping("/search")
    public JSONObject searchStore(@RequestBody JSONObject body) {
        JSONObject data= body.getJSONObject("data");
        return storeService.searchStoreV1(data);
    }
}
