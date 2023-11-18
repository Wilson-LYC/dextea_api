package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Setting;

import java.util.Map;


public interface SettingService {
    public Setting get(String key);
}
