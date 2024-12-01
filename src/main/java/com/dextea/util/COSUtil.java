package com.dextea.util;
import cn.hutool.core.util.IdUtil;
import com.qcloud.cos.*;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class COSUtil {
    //对象存储信息
    private String BUCKET_NAME = "dextea-1313412108";
    private String BUCKET_REGION = "ap-guangzhou";
    private String BUCKET_URL = "https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/";
    private String SECRET_ID;
    private String SECRET_KEY;
    private COSCredentials cred;
    private Region region;
    ClientConfig clientConfig;

    public COSUtil() {
        // 从环境变量中获取连接参数
        this.SECRET_ID = System.getenv("SecretId");
        this.SECRET_KEY = System.getenv("SecretKey");
        this.cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        this.region = new Region(BUCKET_REGION);
        this.clientConfig = new ClientConfig(region);
    }

    /**
     * multipartFile转File
     * @param multipartFile 上传的文件
     * @return 上传的文件
     * @throws IOException IO异常
     */
    private File transferToFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = originalFilename.split("\\.")[0];
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = File.createTempFile(prefix, suffix);
        multipartFile.transferTo(file);
        return file;
    }

    /**
     * 上传文件
     * @param file 上传的文件
     * @param mode 上传模式
     * @return 上传结果
     * @throws IOException IO异常
     */
    public String upload(MultipartFile file,int mode) throws IOException {
        COSClient cosClient = new COSClient(cred, clientConfig);
        File localFile=transferToFile(file);
        String key=null;
        switch (mode){
            case 0:
                key="store/";
                break;
            case 1:
                key="customer/";
                break;
            default:
                key="other/";
                break;
        }
        key+= IdUtil.objectId()+"-"+file.getOriginalFilename();
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String flag=putObjectResult.getETag();
        cosClient.shutdown();
        if(flag!=null){
            return BUCKET_URL+key;
        }else{
            return null;
        }
    }

    /**
     * 删除文件
     * @param url 文件的url
     * @return 删除结果
     */
    public Boolean delete(String url){
        String key=url.replace(BUCKET_URL,"");
        COSClient cosClient = new COSClient(cred, clientConfig);
        cosClient.deleteObject(BUCKET_NAME,key);
        cosClient.shutdown();
        return true;
    }
}