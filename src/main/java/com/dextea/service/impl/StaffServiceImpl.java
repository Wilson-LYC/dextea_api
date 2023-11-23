package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.StaffMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Staff;
import com.dextea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    StoreMapper storeMapper;
    /**
     * 添加员工
     * @param staff
     * @return
     */
    @Override
    public JSONObject addStaff(Staff staff) {
        JSONObject res=new JSONObject();
        int resnum=staffMapper.add(staff);
        if(resnum==1){
            res.put("code",200);
            res.put("msg","添加成功");
        }else{
            res.put("code",500);
            res.put("msg","添加失败");
        }
        return res;
    }

    /**
     * 获取所有员工
     * @return
     */
    @Override
    public JSONObject getAllStaff() {
        JSONObject res=new JSONObject();
        List<Staff> staffList=staffMapper.getAllStaff();
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("staff",list2jsonArray(staffList));
        res.put("data",data);
        return res;
    }

    /**
     * 将staff转换为json
     * @param staff
     * @return
     */
    @Override
    public JSONObject staff2json(Staff staff) {
        JSONObject res=JSONObject.from(staff);
        //门店员工获取店铺名
        if(staff.getRole().equals("2")){
            String storeName=storeMapper.getStoreById(staff.getStoreId()).getName();
            res.put("storeName",storeName);
        }else{
            res.put("storeName","");
        }
        //权限由字符串变json
        //res.put("auth",JSONObject.parseObject(staff.getAuth()));
        //删除auth
        res.remove("auth");
        return res;
    }

    /**
     * 将list转换为jsonArray
     * @param list
     * @return
     */
    @Override
    public JSONArray list2jsonArray(List<Staff> list) {
        JSONArray res=new JSONArray();
        for(Staff staff:list){
            res.add(staff2json(staff));
        }
        return res;
    }

    /**
     * 判断账号是否存在
     * @param account
     * @return
     */
    @Override
    public JSONObject accountExist(String account) {
        JSONObject res=new JSONObject();
        Staff staff=staffMapper.getStaffByAccount(account);
        if(staff==null){
            res.put("code",200);
            res.put("msg","账号不存在");
        }else{
            res.put("code",500);
            res.put("msg","账号已存在");
        }
        return res;
    }

    @Override
    public JSONObject updateStaff(Staff staff) {
        JSONObject res=new JSONObject();
        int resnum=staffMapper.updateStaff(staff);
        if(resnum==1){
            res.put("code",200);
            res.put("msg","修改成功");
        }else{
            res.put("code",500);
            res.put("msg","修改失败");
        }
        return res;
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @Override
    public JSONObject deleteStaffById(int id) {
        JSONObject res=new JSONObject();
        int resnum=staffMapper.deleteStaffById(id);
        if(resnum==1){
            res.put("code",200);
            res.put("msg","删除成功");
        }else{
            res.put("code",500);
            res.put("msg","删除失败");
        }
        return res;
    }

    /**
     * 获取指定商店的员工
     * @param storeId
     * @return
     */
    @Override
    public JSONObject getStaffByStoreId(int storeId) {
        JSONObject res=new JSONObject();
        List<Staff> staffList=staffMapper.getStaffByStoreId(storeId);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("staff",list2jsonArray(staffList));
        res.put("data",data);
        return res;
    }

    /**
     * 搜索员工
     * @param staff
     * @return
     */
    @Override
    public JSONObject searchStaff(Staff staff) {
        JSONObject res=new JSONObject();
        List<Staff> staffList=staffMapper.searchStaff(staff);
        res.put("code",200);
        res.put("msg","成功");
        JSONObject data=new JSONObject();
        data.put("staff",list2jsonArray(staffList));
        res.put("data",data);
        return res;
    }
}
