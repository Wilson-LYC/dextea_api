package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.SettingMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Setting;
import com.dextea.pojo.Store;
import com.dextea.service.SettingService;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    SettingMapper settingMapper;

    @Override
    public Setting get(String key) {
        Setting setting=settingMapper.get(key);
        return setting;
    }
}
