package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.Utils.COSUtils;
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
            COSUtils cosUtils=new COSUtils();
            cosUtils.delete(url);
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
            COSUtils cosUtils = new COSUtils();
            String url=cosUtils.upload(file,1);
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
        }
        return res;
    }

    /**
     * 上传图片
     * @param img 图片文件
     * @return 图片url
     */
    @Override
    public JSONObject uploadImg(MultipartFile img) {
        JSONObject res=new JSONObject();
        try {
            COSUtils cosUtils = new COSUtils();
            String url=cosUtils.upload(img,1);
            if(url!=null) {
                int flag= imgMapper.addImg(url);
                if(flag==0){
                    res.put("code",500);
                    res.put("msg","图片成功上传至腾讯云但为记录在数据库中");
                }else {
                    res.put("code", 200);
                    res.put("msg", "上传成功");
                    JSONObject data=new JSONObject();
                    data.put("url",url);
                    res.put("data", data);
                }
            }
            else{
                res.put("code",500);
                res.put("msg","链接获取失败");
            }
        } catch (Exception e) {
            res.put("code",500);
            res.put("msg","腾讯云服务器异常");
        }
        return res;
    }

    /**
     * 获取所有图片
     * @return 所有图片
     */
    @Override
    public JSONObject getAllImg() {
        JSONObject res=new JSONObject();
        List<Img> imgList= imgMapper.getAllImg();
        res.put("code",200);
        res.put("msg","获取成功");
        JSONObject data=new JSONObject();
        data.put("img",imgList);
        res.put("data",data);
        return res;
    }

    /**
     * 通过url删除图片
     * @param url 图片url
     * @return 删除结果
     */
    @Override
    public JSONObject deleteImgByUrl(String url) {
        JSONObject res=new JSONObject();
        try{
            COSUtils cosUtils=new COSUtils();
            Boolean flag=cosUtils.delete(url);
            if(flag){
                int flag2= imgMapper.deleteImgByUrl(url);
                if(flag2==0){
                    res.put("code",500);
                    res.put("msg","图片成功从腾讯云删除但未从数据库中删除");
                }else{
                    res.put("code",200);
                    res.put("msg","删除成功");
                }
            }else{
                res.put("code",500);
                res.put("msg","图片删除失败");
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","腾讯云服务器异常");
        }
        return res;
    }
}
