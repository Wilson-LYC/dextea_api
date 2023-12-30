package com.dextea.Utils;
import cn.hutool.core.util.IdUtil;
import com.qcloud.cos.*;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class COSUtils {
    // 1 初始化用户身份信息（secretId, secretKey）。
    // SECRETID 和 SECRETKEY 请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
    String secretId = "";//用户的 SecretId，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。子账号密钥获取可参见 https://cloud.tencent.com/document/product/598/37140
    String secretKey = "";//用户的 SecretKey，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。子账号密钥获取可参见 https://cloud.tencent.com/document/product/598/37140
    COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
    // 2 设置 bucket 的地域, COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
    // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
    Region region = new Region("");
    ClientConfig clientConfig = new ClientConfig(region);

    // 指定文件将要存放的存储桶
    String bucketName = "";

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
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String flag=putObjectResult.getETag();
        cosClient.shutdown();
        if(flag!=null){
            return "https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/"+key;
        }else{
            return null;
        }
    }

    public Boolean delete(String url){
        String key=url.replace("https://dextea-1313412108.cos.ap-guangzhou.myqcloud.com/","");
        COSClient cosClient = new COSClient(cred, clientConfig);
        cosClient.deleteObject(bucketName,key);
        cosClient.shutdown();
        return true;
    }
}
