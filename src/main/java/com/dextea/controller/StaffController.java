package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;
import com.dextea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    //添加员工
    @PostMapping("/add")
    public JSONObject add(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Staff staff=new Staff();
        staff.setAccount(data.getString("account"));
//        String hashedPassword = passwordEncoder.encode(data.getString("password"));
//        staff.setPassword(hashedPassword);
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
    @GetMapping("/get/all")
    public JSONObject getAllStaff(){
        return staffService.getAllStaff();
    }
    //判断账号是否存在
    @GetMapping("/account/exist")
    public JSONObject accountExist(@RequestParam("account") String account){
        return staffService.accountExist(account);
    }
    //修改员工信息
    @PostMapping("/update")
    public JSONObject updateStaff(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Staff staff=new Staff();
        staff.setId(data.getInteger("id"));
        staff.setAccount(data.getString("account"));
//        String hashedPassword = passwordEncoder.encode(data.getString("password"));
//        staff.setPassword(hashedPassword);
        staff.setPassword(data.getString("password"));
        staff.setRole(data.getString("role"));
        if(data.getInteger("storeId")==null){
            staff.setStoreId(0);
        }else {
            staff.setStoreId(data.getInteger("storeId"));
        }
        staff.setName(data.getString("name"));
        return staffService.updateStaff(staff);
    }

    //删除员工
    @GetMapping("/delete")
    public JSONObject deleteStaff(@RequestParam("id") int id){
        return staffService.deleteStaffById(id);
    }
    //获取指定商店的员工
    @GetMapping("/get/samestore")
    public JSONObject getStaffByStoreId(@RequestParam("sid") int storeId){
        return staffService.getStaffByStoreId(storeId);
    }

    //搜索员工
    @PostMapping("/search")
    public JSONObject searchStaff(@RequestBody JSONObject json){
        JSONObject data=json.getJSONObject("data");
        Staff staff=new Staff();
        if (data.getInteger("id")!=null){
            staff.setId(data.getInteger("id"));
        }
        if(data.getString("account")!=null){
            staff.setAccount(data.getString("account"));
        }
        if(data.getString("name")!=null){
            staff.setName(data.getString("name"));
        }
        if(data.getString("role")!=null){
            staff.setRole(data.getString("role"));
        }
        if(data.getInteger("storeId")!=null){
            staff.setStoreId(data.getInteger("storeId"));
        }
        return staffService.searchStaff(staff);
    }
}
