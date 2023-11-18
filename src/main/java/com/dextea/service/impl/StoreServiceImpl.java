package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.SettingMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Setting;
import com.dextea.pojo.Store;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    SettingMapper settingMapper;
    @Override
    public JSONObject getAll(){
        JSONObject res=new JSONObject();
        List<Store> list=storeMapper.getAll();
        res.put("tabledata",list);
        return res;
    }

    /**
     * 获取营业区域
     * @return 营业区域
     */
    @Override
    public JSONObject getOpenArea() {
        JSONObject res=new JSONObject();
        Setting setting=settingMapper.get("open_area");
        //判断是否获取到营业区域
        if(setting==null){
            res.put("code",500);
            res.put("msg","失败");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("openArea",JSONArray.parseArray(setting.getValue()));
        res.put("data",data);
        return res;
    }
}
