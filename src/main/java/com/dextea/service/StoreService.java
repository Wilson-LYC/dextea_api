package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;


public interface StoreService {
    public JSONObject getAll();
    //获取营业区域
    public JSONObject getOpenArea();
}
