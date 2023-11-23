package com.dextea.mapper;

import com.dextea.pojo.CommCate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommCateMapper {
    //给品类添加商品
    int addCommToCate(int cateId, int commId);

    //通过commid查询品类
    List<CommCate> getCateByCommId(int commId);
}
