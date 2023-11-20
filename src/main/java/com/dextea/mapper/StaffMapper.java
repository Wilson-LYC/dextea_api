package com.dextea.mapper;

import com.dextea.pojo.Staff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StaffMapper{
    int add(Staff staff);
    List<Staff> getAllStaff();
}
