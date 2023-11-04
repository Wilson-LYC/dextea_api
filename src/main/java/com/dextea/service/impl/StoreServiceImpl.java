package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Store;
import com.dextea.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    @Override
    public JSONObject getAll(){
        JSONObject res=new JSONObject();
        List<Store> list=storeMapper.getAll();
        res.put("tabledata",list);
        return res;
    }
}
