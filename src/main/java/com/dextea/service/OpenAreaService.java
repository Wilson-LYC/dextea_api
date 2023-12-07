package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public interface OpenAreaService {
    //获取营业区域
    JSONObject getOpenAreaV1();
    //更新营业区域
    JSONObject updateOpenAreaV1(JSONArray data);
    //获取营业区域下拉选项
    JSONObject getOpenAreaAsSelectOptionV1();
    //小程序获取营业区域
    JSONObject getOpenAreaForCustomerV1();
}
