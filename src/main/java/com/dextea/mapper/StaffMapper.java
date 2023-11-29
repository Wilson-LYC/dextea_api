package com.dextea.mapper;

import com.dextea.pojo.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffMapper {
    int addStaff(Staff staff);
    void deleteStaffById(int id);
    void updateStaffById(@Param("id") int id,@Param("account")String account,@Param("password")String password,@Param("role")int role,@Param("store_id")int store_id,@Param("auth")String auth);
    List<Staff> getStaff();
    Staff getStaffById(int id);
}
