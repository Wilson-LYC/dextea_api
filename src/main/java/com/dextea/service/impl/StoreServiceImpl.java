package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.SettingMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Setting;
import com.dextea.pojo.Store;
import com.dextea.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    SettingMapper settingMapper;

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

    /**
     * 更新营业区域
     * @param data 营业区域
     * @return 更新结果
     */
    @Override
    public JSONObject updateOpenArea(JSONArray data) {
        JSONObject res=new JSONObject();
        Setting setting=new Setting();
        setting.setKey("open_area");
        setting.setValue(data.toJSONString());
        int result=settingMapper.update(setting);
        if(result==1){
            res.put("code",200);
            res.put("msg","成功");
        }else{
            res.put("code",500);
            res.put("msg","失败");
        }
        return res;
    }

    /**
     * 获取所有店铺
     * @return 所有店铺
     */
    @Override
    public JSONObject getAllStore() {
        JSONObject res=new JSONObject();
        res.put("code",200);
        res.put("msg","成功");
        //获取所有店铺
        List<Store> stores=storeMapper.getAllStore();
        JSONObject data=new JSONObject();
        data.put("stores",stores);
        //获取营业区域
        Setting setting=settingMapper.get("open_area");
        JSONArray openAreaRaw=JSONArray.parseArray(setting.getValue());
        JSONArray openArea=new JSONArray();
        for(int i=0;i<openAreaRaw.size();i++){
            JSONObject areaOld=openAreaRaw.getJSONObject(i);
            JSONObject areaNew=new JSONObject();
            areaNew.put("label",areaOld.getString("value"));
            areaNew.put("value",areaOld.getString("value"));
            JSONArray chileOld=areaOld.getJSONArray("children");
            JSONArray chileNew=new JSONArray();
            for(int j=0;j<chileOld.size();j++){
                JSONObject c1=chileOld.getJSONObject(j);
                JSONObject c2=new JSONObject();
                c2.put("label",c1.getString("value"));
                c2.put("value",c1.getString("value"));
                c2.put("children",new JSONArray());
                chileNew.add(c2);
            }
            areaNew.put("children",chileNew);
            openArea.add(areaNew);
        }
        data.put("openArea",openArea);
        res.put("data",data);
        return res;
    }

    /**
     * 添加店铺
     * @param store 店铺
     * @return 添加结果
     */
    @Override
    public JSONObject addStore(Store store) {
        JSONObject res=new JSONObject();
        int result=storeMapper.add(store);
        if(result==1){
            res.put("code",200);
            res.put("msg","成功");
        }else{
            res.put("code",500);
            res.put("msg","添加失败");
        }
        return res;
    }
}
