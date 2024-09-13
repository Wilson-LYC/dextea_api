package com.dextea.controller;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1/manage/img")
public class ImgControllerV1 {
    @Autowired
    ImgService imgService;
    @Autowired
    HttpServletResponse response;
    //获取图片列表
    @GetMapping("/list")
    public JSONObject getImgList(){
        return imgService.getAllImgV1();
    }
    //删除图片
    @PostMapping("/delete")
    public JSONObject deleteImg(@RequestBody JSONObject body){
        JSONObject data=body.getJSONObject("data");
        return imgService.deleteImgByUrlV1(data.getString("url"));
    }
    //上传图片
    @PostMapping("/upload")
    public JSONObject uploadImg(MultipartFile file){
        JSONObject res= imgService.uploadImgV1(file);
        int code=res.getInteger("code");
        response.setStatus(code);
        return res;
    }
}
