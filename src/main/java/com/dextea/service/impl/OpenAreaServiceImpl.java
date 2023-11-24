package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.SettingMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Setting;
import com.dextea.service.OpenAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAreaServiceImpl implements OpenAreaService {
    @Autowired
    SettingMapper settingMapper;
    @Autowired
    StoreMapper storeMapper;
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
        JSONArray openArea=JSONArray.parseArray(setting.getValue());
        JSONArray openAreaNew=new JSONArray();
        for (int i=0;i<openArea.size();i++){
            JSONObject area1= openArea.getJSONObject(i);
            JSONObject area2=new JSONObject();
            int num1=storeMapper.countStoreByArea(area1.getString("value"));
            area2.put("value",area1.getString("value"));
            area2.put("num",num1);
            JSONArray children1=area1.getJSONArray("children");
            JSONArray children2=new JSONArray();
            for(int j=0;j<children1.size();j++){
                JSONObject c1=children1.getJSONObject(j);
                JSONObject c2=new JSONObject();
                c2.put("value",c1.getString("value"));
                c2.put("children",new JSONArray());
                int num2=storeMapper.countStoreByArea(c1.getString("value"));
                c2.put("num",num2);
                children2.add(c2);
            }
            area2.put("children",children2);
            openAreaNew.add(area2);
        }
        data.put("openArea",openAreaNew);
        res.put("data",data);
        return res;
    }


    /**
     * 获取营业区域下拉选项
     * @return json
     */
    @Override
    public JSONObject getOpenAreaAsSelectOption() {
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
        //获取营业区域
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
}
