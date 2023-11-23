package com.dextea.mapper;

import com.dextea.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int addCategory(Category category);

    List<Category> getAllCategory();

    String getCateNameById(int cateId);
}
