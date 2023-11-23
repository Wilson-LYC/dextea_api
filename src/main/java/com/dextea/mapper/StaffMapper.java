package com.dextea.mapper;

import com.dextea.pojo.Staff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StaffMapper{
    int add(Staff staff);
    List<Staff> getAllStaff();
    Staff getStaffByAccount(String account);
    //修改员工信息
    int updateStaff(Staff staff);

    int deleteStaffById(int id);

    List<Staff> getStaffByStoreId(int storeId);

    List<Staff> searchStaff(Staff staff);
}
