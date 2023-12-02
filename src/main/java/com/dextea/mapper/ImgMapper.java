package com.dextea.mapper;

import com.dextea.pojo.Img;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgMapper {
    int addImg(String url);

    List<Img> getAllImg();

    int deleteImgByUrl(String url);
}
