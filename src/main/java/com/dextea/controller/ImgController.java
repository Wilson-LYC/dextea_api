package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.Utils.COSUtils;
import com.dextea.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
public class ImgController {
    @Autowired
    ImgService imgService;
    @PostMapping("/upload")
    public JSONObject uploadImg(MultipartFile file){
        return imgService.uploadImg(file);
    }
    //获取所有图片
    @GetMapping("/get/all")
    public JSONObject getAllImg(){
        return imgService.getAllImg();
    }
}
