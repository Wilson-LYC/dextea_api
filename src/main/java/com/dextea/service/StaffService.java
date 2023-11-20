package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;

import java.util.List;

public interface StaffService {
    JSONObject addStaff(Staff staff);
    JSONObject getAllStaff();

    JSONArray list2jsonArray(List<Staff> list);
}
