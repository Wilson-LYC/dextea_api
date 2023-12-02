package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public interface OpenAreaService {
    //获取营业区域
    public JSONObject getOpenArea();
    JSONObject getOpenAreaV1();
    //更新营业区域
    JSONObject updateOpenAreaV1(JSONArray data);
    JSONObject updateOpenArea(JSONArray openArea);
    //获取营业区域下拉选项
    JSONObject getOpenAreaAsSelectOption();
    JSONObject getOpenAreaAsSelectOptionV1();

    JSONObject getOpenAreaForCustomer();



}
