package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/manage/staff")
public class StaffControllerV1 {
    @Autowired
    StaffService staffService;
    //新增员工
    @PostMapping("/add")
    public JSONObject addStaff(@RequestBody JSONObject body) {
        JSONObject data = body.getJSONObject("data");
        return staffService.addStaffV1(data);
    }
    //获取所有员工
    @GetMapping("/all")
    public JSONObject getAllStaff() {
        return staffService.getAllStaffV1();
    }
    //根据门店id获取员工
    @GetMapping("/samestore")
    public JSONObject getStaffByStoreId(@RequestParam int id) {
        return staffService.getStaffByStoreIdV1(id);
    }
    //判断账号是否存在
    @GetMapping("/account")
    public JSONObject accountExist(@RequestParam String account) {
        return staffService.accountExistV1(account);
    }
    //搜索员工
    @PostMapping("/search")
    public JSONObject searchStaff(@RequestBody JSONObject body) {
        JSONObject data = body.getJSONObject("data");
        return staffService.searchStaffV1(data);
    }
    //删除员工
    @GetMapping("/delete")
    public JSONObject deleteStaffById(@RequestParam int id) {
        return staffService.deleteStaffByIdV1(id);
    }
    //更新员工
    @PostMapping("/update")
    public JSONObject updateStaff(@RequestBody JSONObject body) {
        JSONObject data = body.getJSONObject("data");
        return staffService.updateStaffV1(data);
    }
}
