package com.dextea.mapper;

import com.dextea.pojo.CommCate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommCateMapper {

    List<CommCate> getCateByCommId(int commId);
}
