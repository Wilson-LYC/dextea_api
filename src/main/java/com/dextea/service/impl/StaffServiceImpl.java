package com.dextea.service.impl;

import cn.hutool.crypto.SecureUtil;
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
     * 将staff转换为json
     * @param staff 员工
     * @return json
     */
    @Override
    public JSONObject toJson(Staff staff) {
        JSONObject res=JSONObject.from(staff);
        //移除密码
        res.remove("password");
        return res;
    }

    /**
     * 将list转换为jsonArray
     * @param list 员工列表
     * @return jsonArray
     */
    @Override
    public JSONArray toJson(List<Staff> list) {
        JSONArray res=new JSONArray();
        for(Staff staff:list){
            res.add(toJson(staff));
        }
        return res;
    }

    /**
     * json转staff
     * @param json json
     * @return staff
     */
    @Override
    public Staff toStaff(JSONObject json) {
        Staff staff=new Staff();
        if (json.getInteger("id")!=null)
            staff.setId(json.getInteger("id"));
        if (json.getString("account")!=null)
            staff.setAccount(json.getString("account"));
        if (json.getString("password")!=null)
            staff.setPassword(json.getString("password"));
        if (json.getString("name")!=null)
            staff.setName(json.getString("name"));
        if(json.getString("role")!=null)
            staff.setRole(json.getString("role"));
        if(json.getInteger("storeId")!=null)
            staff.setStoreId(json.getInteger("storeId"));
        if(json.getString("storeName")!=null)
            staff.setStoreName(json.getString("storeName"));
        if (json.getString("createtime")!=null)
            staff.setCreatetime(json.getString("createtime"));
        if (json.getString("updatetime")!=null)
            staff.setUpdatetime(json.getString("updatetime"));
        return staff;
    }

    /**
     * 添加员工v1
     * @param data 员工信息
     * @return 添加结果
     */
    @Override
    public JSONObject addStaffV1(JSONObject data) {
        JSONObject res=new JSONObject();
        Staff staff=toStaff(data);
        staff.setPassword(SecureUtil.md5(staff.getPassword()));//密码MD5加密
        try{
            staffMapper.add(staff);
            res.put("code",200);
            res.put("msg","添加成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","添加失败");
        }
        return res;
    }

    /**
     * 获取所有员工v1
     * @return 所有员工
     */
    @Override
    public JSONObject getAllStaffV1() {
        JSONObject res=new JSONObject();
        try{
            List<Staff> staffList=staffMapper.getAllStaff();
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data=new JSONObject();
            data.put("staff",toJson(staffList));
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","失败");
        }
        return res;
    }

    /**
     * 根据门店id获取员工v1
     * @param id 门店id
     * @return 员工
     */
    @Override
    public JSONObject getStaffByStoreIdV1(int id) {
        JSONObject res=new JSONObject();
        try{
            List<Staff> staffList=staffMapper.getStaffByStoreId(id);
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data=new JSONObject();
            data.put("staff",toJson(staffList));
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","失败");
        }
        return res;
    }

    /**
     * 判断账号是否存在v1
     * @param account 账号
     * @return 判断结果
     */
    @Override
    public JSONObject accountExistV1(String account) {
        JSONObject res=new JSONObject();
        try{
            Staff staff=staffMapper.getStaffByAccount(account);
            if(staff==null){
                res.put("code",200);
                res.put("msg","账号可用");
            }else{
                res.put("code",500);
                res.put("msg","账号已存在");
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","失败");
        }
        return res;
    }

    /**
     * 搜索员工v1
     * @param data 搜索条件
     * @return 搜索结果
     */
    @Override
    public JSONObject searchStaffV1(JSONObject data) {
        JSONObject res=new JSONObject();
        try{
            Staff staff=toStaff(data);
            List<Staff> staffList=staffMapper.searchStaff(staff);
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data1=new JSONObject();
            data1.put("staff",toJson(staffList));
            res.put("data",data1);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","失败");
        }
        return res;
    }

    /**
     * 删除员工v1
     * @param id 员工id
     * @return 删除结果
     */
    @Override
    public JSONObject deleteStaffByIdV1(int id) {
        JSONObject res=new JSONObject();
        try{
            staffMapper.deleteStaffById(id);
            res.put("code",200);
            res.put("msg","删除成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","删除失败");
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * 更新员工v1
     * @param data 员工信息
     * @return 更新结果
     */
    @Override
    public JSONObject updateStaffV1(JSONObject data) {
        JSONObject res=new JSONObject();
        try{
            Staff staff=toStaff(data);
            //密码MD5加密
            if(staff.getPassword()!=null&& !staff.getPassword().isEmpty()) {
                staff.setPassword(SecureUtil.md5(staff.getPassword()));
            }
            staffMapper.updateStaff(staff);
            res.put("code",200);
            res.put("msg","修改成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","修改失败");
        }
        return res;
    }
}
