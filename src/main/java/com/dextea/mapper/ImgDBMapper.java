package com.dextea.mapper;

import com.dextea.pojo.ImgDB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgDBMapper {
    int addImg(String url);

    List<ImgDB> getAllImg();

    int deleteImgByUrl(String url);
}
