package com.dextea.service.impl;

import cn.hutool.json.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.Utils.COSUtils;
import com.dextea.mapper.ImgDBMapper;
import com.dextea.pojo.ImgDB;
import com.dextea.service.ImgService;
import com.qcloud.cos.COS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgDBMapper imgDBMapper;
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
                int flag=imgDBMapper.addImg(url);
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
        List<ImgDB> imgList=imgDBMapper.getAllImg();
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
                int flag2=imgDBMapper.deleteImgByUrl(url);
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
