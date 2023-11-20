package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.StaffMapper;
import com.dextea.pojo.Staff;
import com.dextea.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;

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
     * 将list转换为jsonArray
     * @param list
     * @return
     */
    @Override
    public JSONArray list2jsonArray(List<Staff> list) {
        JSONArray res=new JSONArray();
        for(Staff staff:list){
            JSONObject temp=new JSONObject();
            temp.put("id",staff.getId());
            temp.put("account",staff.getAccount());
            temp.put("password",staff.getPassword());
            temp.put("role",staff.getRole());
            temp.put("storeId",staff.getStoreId());
            //权限由字符串变json
            temp.put("auth",JSONObject.parseObject(staff.getAuth()));
            res.add(temp);
        }
        return res;
    }
}
