package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;
import com.dextea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")//允许跨域
public class StaffController {
    @Autowired
    StaffService staffService;
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject data){
        Staff staff=new Staff();
        staff.setAccount(data.getString("account"));
        staff.setPassword(data.getString("password"));
        staff.setRole(data.getInteger("role"));
        if(data.getInteger("storeId")==null){
            staff.setStoreId(0);
        }else {
            staff.setStoreId(data.getInteger("storeId"));
        }
        staff.setAuth(data.getString("auth"));
        return staffService.addStaff(staff);
    }

    @GetMapping("/get/all")
    public JSONObject getAllStaff(){
        return staffService.getAllStaff();
    }
}
