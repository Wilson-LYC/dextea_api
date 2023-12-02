package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    //上传图片
    JSONObject uploadImg(MultipartFile img);
    JSONObject uploadImgV1(MultipartFile file);
    //获取图片列表
    JSONObject getAllImgV1();
    JSONObject getAllImg();
    //删除图片
    JSONObject deleteImgByUrlV1(String url);
    JSONObject deleteImgByUrl(String url);



}
