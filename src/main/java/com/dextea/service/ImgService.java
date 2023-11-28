package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    JSONObject uploadImg(MultipartFile img);

    JSONObject getAllImg();

    JSONObject deleteImgByUrl(String url);
}
