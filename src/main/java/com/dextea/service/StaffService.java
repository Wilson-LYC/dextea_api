package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;

import java.util.List;

public interface StaffService {
    //staff转json
    JSONArray toJson(List<Staff> list);
    JSONObject toJson(Staff staff);
    //json转staff
    Staff toStaff(JSONObject json);
    //新增员工
    JSONObject addStaffV1(JSONObject data);
    //获取所有员工
    JSONObject getAllStaffV1();
    //根据门店id获取员工
    JSONObject getStaffByStoreIdV1(int id);
    //判断账号是否存在
    JSONObject accountExistV1(String account);
    //搜索员工
    JSONObject searchStaffV1(JSONObject data);
    //删除员工
    JSONObject deleteStaffByIdV1(int id);
    //更新员工
    JSONObject updateStaffV1(JSONObject data);
}
