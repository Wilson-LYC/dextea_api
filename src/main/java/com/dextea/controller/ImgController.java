//package com.dextea.controller;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.dextea.Utils.COSUtils;
//import com.dextea.service.ImgService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//@RequestMapping("/img")
//public class ImgController {
//    @Autowired
//    ImgService imgService;
//    @Autowired
//    HttpServletResponse response;
//    @PostMapping("/upload")
//    public JSONObject uploadImg(MultipartFile file){
//        JSONObject res= imgService.uploadImg(file);
//        int code=res.getInteger("code");
//        response.setStatus(code);
//        return res;
//    }
//    //获取所有图片
//    @GetMapping("/get/all")
//    public JSONObject getAllImg(){
//        return imgService.getAllImg();
//    }
//
//    @PostMapping("/delete")
//    public JSONObject deleteImgByUrl(@RequestBody JSONObject json){
//        JSONObject data=json.getJSONObject("data");
//        String url=data.getString("url");
//        return imgService.deleteImgByUrl(url);
//    }
//}
