package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/customer")
public class CustomerControllerV1 {
    @Autowired
    CustomerService customerService;
    //获取所有顾客
    @GetMapping("/list")
    public JSONObject list(){
        return customerService.getAllCustomerV1();
    }
    //更新顾客信息
    @PostMapping("/update")
    public JSONObject update(@RequestBody JSONObject body){
        JSONObject data=body.getJSONObject("data");
        return customerService.updateCustomerV1(data);
    }
    //搜索顾客
    @PostMapping("/search")
    public JSONObject search(@RequestBody JSONObject body){
        JSONObject data=body.getJSONObject("data");
        return customerService.searchCustomerV1(data);
    }
    //删除顾客
    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("id") int id){
        return customerService.deleteCustomerV1(id);
    }
}
