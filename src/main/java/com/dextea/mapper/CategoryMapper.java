package com.dextea.mapper;

import com.dextea.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //获取所有品类
    List<Category> getAllCategory();
    //根据id获取品类名
    String getCateNameById(int cateId);

    int addCategory(Category category);

    int updateCategory(Category category);
}
