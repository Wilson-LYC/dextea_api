package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.util.COSUtil;
import com.dextea.mapper.ImgMapper;
import com.dextea.pojo.Img;
import com.dextea.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgMapper imgMapper;

    /**
     * 获取图片列表v1
     * @return 图片列表
     */
    @Override
    public JSONObject getAllImgV1() {
        JSONObject res=new JSONObject();
        try{
            List<Img> imgList= imgMapper.getAllImg();
            res.put("code",200);
            res.put("msg","获取成功");
            JSONObject data=new JSONObject();
            data.put("img",imgList);
            res.put("data",data);
        }catch (Exception e) {
            res.put("code", 500);
            res.put("msg", "数据库异常");
        }
        return res;
    }

    /**
     * 删除图片v1
     * @param url 图片url
     * @return 删除结果
     */
    @Override
    public JSONObject deleteImgByUrlV1(String url) {
        JSONObject res=new JSONObject();
        try{
            COSUtil cosUtil =new COSUtil();
            cosUtil.delete(url);
            try{
                imgMapper.deleteImgByUrl(url);
                res.put("code",200);
                res.put("msg","删除成功");
            }catch (Exception e){
                res.put("code",500);
                res.put("msg","数据库异常");
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","腾讯云服务器异常");
            res.put("error",e.getMessage());
        }
        return res;
    }

    /**
     * 上传图片v1
     * @param file 图片文件
     * @return 图片url
     */
    @Override
    public JSONObject uploadImgV1(MultipartFile file) {
        JSONObject res=new JSONObject();
        try {
            COSUtil cosUtil = new COSUtil();
            String url= cosUtil.upload(file,1);
            try{
                imgMapper.addImg(url);
                res.put("code",200);
                res.put("msg","上传成功");
                JSONObject data=new JSONObject();
                data.put("url",url);
                res.put("data", data);
            }catch (Exception e){
                res.put("code",500);
                res.put("msg","数据库异常");
            }
        } catch (Exception e) {
            res.put("code",500);
            res.put("msg","腾讯云服务器异常");
            res.put("error",e.getMessage());
        }
        return res;
    }
}
