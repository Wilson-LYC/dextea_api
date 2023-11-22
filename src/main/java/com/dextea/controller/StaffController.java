package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;
import com.dextea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company/staff")
@CrossOrigin(origins = "*")
public class StaffController {
    @Autowired
    StaffService staffService;
    //添加员工
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Staff staff=new Staff();
        staff.setAccount(data.getString("account"));
        staff.setPassword(data.getString("password"));
        staff.setRole(data.getString("role"));
        if(data.getInteger("storeId")==null){
            staff.setStoreId(0);
        }else {
            staff.setStoreId(data.getInteger("storeId"));
        }
        staff.setName(data.getString("name"));
        return staffService.addStaff(staff);
    }
    //获取所有员工
    @GetMapping("/get")
    public JSONObject getAllStaff(){
        return staffService.getAllStaff();
    }
    //判断账号是否存在
    @GetMapping("/account/exist")
    public JSONObject accountExist(@RequestParam("account") String account){
        return staffService.accountExist(account);
    }
}
