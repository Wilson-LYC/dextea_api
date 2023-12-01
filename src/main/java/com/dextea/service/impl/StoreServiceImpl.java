package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.SettingMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Store;
import com.dextea.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    SettingMapper settingMapper;

    /**
     * store转换为json
     * @return JSONObject
     */
    @Override
    public JSONObject toJson(Store store) {
        JSONObject res=new JSONObject();
        res.put("id",store.getId());
        res.put("name",store.getName());
        JSONArray area=JSONArray.parseArray(store.getArea());
        res.put("area",area);
        res.put("address",store.getAddress());
        res.put("phone",store.getPhone());
        res.put("openTime",store.getOpenTime());
        res.put("openState",store.getOpenState());
        return res;
    }

    /**
     * storeList转换为json
     * @return JSONArray
     */
    @Override
    public JSONArray toJson(List<Store> storeList) {
        JSONArray res=new JSONArray();
        for(Store store:storeList){
            res.add(toJson(store));
        }
        return res;
    }

    /**
     * json转换为store
     * @param jsonObject json
     * @return Store
     */
    @Override
    public Store toStore(JSONObject jsonObject) {
        Store store=new Store();
        if(jsonObject.getInteger("id")!=null)
            store.setId(jsonObject.getInteger("id"));
        if(jsonObject.getString("name")!=null)
            store.setName(jsonObject.getString("name"));
        if(jsonObject.getString("area")!=null&&!Objects.equals(jsonObject.getString("area"), ""))
            store.setArea(jsonObject.getJSONArray("area").toJSONString());
        if(jsonObject.getString("address")!=null)
            store.setAddress(jsonObject.getString("address"));
        if(jsonObject.getString("phone")!=null)
            store.setPhone(jsonObject.getString("phone"));
        if(jsonObject.getString("openTime")!=null)
            store.setOpenTime(jsonObject.getString("openTime"));
        if(jsonObject.getString("openState")!=null)
            store.setOpenState(jsonObject.getString("openState"));
        if (jsonObject.getString("createtime")!=null)
            store.setCreatetime(jsonObject.getString("createtime"));
        if (jsonObject.getString("updatetime")!=null)
            store.setUpdatetime(jsonObject.getString("updatetime"));
        return store;
    }

    /**
     * 新增门店v1
     * @param body json
     * @return JSONObject
     */
    @Override
    public JSONObject addStoreV1(JSONObject body) {
        JSONObject res=new JSONObject();
        Store store=toStore(body);
        try{
            storeMapper.add(store);
            res.put("code",200);
            res.put("msg","新增门店成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","新增门店失败");
        }
        return res;
    }

    /**
     * 获取所有门店v1
     * @return JSONObject
     */
    @Override
    public JSONObject getAllStoreV1() {
        JSONObject res=new JSONObject();
        try{
            List<Store> storeList=storeMapper.getAllStore();
            JSONObject data=new JSONObject();
            data.put("stores",toJson(storeList));
            res.put("code",200);
            res.put("msg","成功");
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","获取门店列表错误");
        }
        return res;
    }

    /**
     * 批量修改门店营业状态v1
     * @param data json
     * @return JSONObject
     */
    @Override
    public JSONObject multipleUpdateOpenStateV1(JSONObject data) {
        JSONObject res=new JSONObject();
        JSONArray idList=data.getJSONArray("list");
        String openState=data.getString("openState");
        try{
            for(int i=0;i<idList.size();i++){
                Store store=new Store();
                store.setId(idList.getInteger(i));
                store.setOpenState(openState);
                storeMapper.updateStore(store);
            }
            res.put("code",200);
            res.put("msg","更新成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","更新失败");
        }
        return res;
    }

    /**
     * 更新店铺信息v1
     * @param data json
     * @return JSONObject
     */
    @Override
    public JSONObject updateStoreV1(JSONObject data) {
        JSONObject res=new JSONObject();
        Store store=toStore(data);
        try{
            storeMapper.updateStore(store);
            res.put("code",200);
            res.put("msg","更新成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","更新失败");
        }
        return res;
    }

    /**
     * 删除店铺v1
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject deleteStoreByIdV1(int id) {
        JSONObject res=new JSONObject();
        try{
            storeMapper.deleteStoreById(id);
            res.put("code",200);
            res.put("msg","删除成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","删除失败");
        }
        return res;
    }

    /**
     * 搜索店铺v1
     * @param data
     * @return
     */
    @Override
    public JSONObject searchStoreV1(JSONObject data) {
        JSONObject res=new JSONObject();
        Store store=toStore(data);
        try{
            List<Store> storeList=storeMapper.searchStore(store);
            JSONObject dataJson=new JSONObject();
            dataJson.put("stores",toJson(storeList));
            res.put("code",200);
            res.put("msg","成功");
            res.put("data",dataJson);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","搜索失败");
        }
        return res;
    }

    /**
     * storeList转换为下拉框选项
     * @return JSONArray
     */
    @Override
    public JSONArray toSelectOption(List<Store> storeList) {
        JSONArray res=new JSONArray();
        for(Store store:storeList){
            JSONObject storeJson=new JSONObject();
            storeJson.put("label",store.getName());
            storeJson.put("value",store.getId());
            res.add(storeJson);
        }
        return res;
    }

    /**
     * 添加店铺
     * @return JSONObject
     */
    @Override
    public JSONObject addStore(Store store) {
        JSONObject res=new JSONObject();
        int result=storeMapper.add(store);
        if(result==1){
            res.put("code",200);
            res.put("msg","新增门店成功");
        }else{
            res.put("code",500);
            res.put("msg","新增门店失败");
        }
        return res;
    }

    /**
     * 删除店铺
     * @return 删除结果
     */
    @Override
    public JSONObject deleteStoreById(int id) {
        JSONObject res=new JSONObject();
        int result=storeMapper.deleteStoreById(id);
        if(result==1){
            res.put("code",200);
            res.put("msg","成功");
        }else{
            res.put("code",500);
            res.put("msg","删除失败");
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
        //获取所有店铺
        List<Store> storeList=storeMapper.getAllStore();
        JSONObject data=new JSONObject();
        data.put("stores",toJson(storeList));
        res.put("code",200);
        res.put("msg","成功");
        res.put("data",data);
        return res;
    }

    /**
     * 通过ID获取店铺
     * @param id 店铺id
     * @return 店铺信息
     */
    @Override
    public JSONObject getStoreById(int id) {
        JSONObject res=new JSONObject();
        Store store=storeMapper.getStoreById(id);
        if(store==null){
            res.put("code",500);
            res.put("msg","门店不存在");
            return res;
        }
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("store",toJson(store));
        res.put("data",data);
        return res;
    }







    /**
     * 搜索店铺
     * @param store 店铺信息
     * @return JSONObject
     */
    @Override
    public JSONObject searchStore(Store store) {
        JSONObject res=new JSONObject();
        List<Store> storeList=storeMapper.searchStore(store);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("stores",toJson(storeList));
        res.put("data",data);
        return res;
    }

    /**
     * 修改店铺信息
     * @param store 店铺信息
     * @return JSONObject
     */
    @Override
    public JSONObject updateStore(Store store) {
        JSONObject res=new JSONObject();
        int result=storeMapper.updateStore(store);
        if(result==1){
            res.put("code",200);
            res.put("msg","修改成功");
        }else{
            res.put("code",500);
            res.put("msg","修改失败");
        }
        return res;
    }

    /**
     * 修改单个门店的营业状态
     * @param id 门店id
     * @param openState 营业状态
     * @return json
     */
    @Override
    public JSONObject updateOpenState(int id, String openState) {
        JSONObject res=new JSONObject();
        Store store=new Store();
        store.setId(id);
        store.setOpenState(openState);
        int result=storeMapper.updateStore(store);
        if(result==1){
            res.put("code",200);
            res.put("msg","成功");
        }else{
            res.put("code",500);
            res.put("msg","修改失败");
        }
        return res;
    }

    /**
     * 修改多个门店的营业状态
     * @param idList 门店id列表
     * @param openState 营业状态
     * @return json
     */
    @Override
    public JSONObject updateOpenState(List<Integer> idList, String openState) {
        JSONObject res=new JSONObject();
        for(int id:idList){
            Store store=new Store();
            store.setId(id);
            store.setOpenState(openState);
            int result=storeMapper.updateStore(store);
            if(result!=1){
                res.put("code",500);
                res.put("msg","门店ID:"+id+",修改失败");
                return res;
            }
        }
        res.put("code",200);
        res.put("msg","成功");
        return res;
    }

    /**
     * 获取店铺选项
     * @return json
     */
    @Override
    public JSONObject getStoreAsSelectOption() {
        JSONObject res=new JSONObject();
        List<Store> storeList=storeMapper.getAllStore();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("stores",toSelectOption(storeList));
        res.put("data",data);
        return res;
    }

    @Override
    public JSONObject getStoreForCustomer(String area) {
        JSONObject res=new JSONObject();
        List<Store> storeList=storeMapper.getStoreForCustomer(area);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("stores",toJson(storeList));
        res.put("data",data);
        return res;
    }
}
