package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;

import java.util.List;

public interface StaffService {
    JSONObject addStaff(Staff staff);
    JSONObject getAllStaff();
    JSONArray list2jsonArray(List<Staff> list);
    JSONObject staff2json(Staff staff);
    JSONObject accountExist(String account);
    JSONObject updateStaff(Staff staff);

    JSONObject deleteStaffById(int id);

    JSONObject getStaffByStoreId(int storeId);

    JSONObject searchStaff(Staff staff);
}
