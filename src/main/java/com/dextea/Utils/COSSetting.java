package com.dextea.Utils;

import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

public class COSSetting {
    final static String BUCKET_NAME = "";//存储桶名
    final static String BUCKET_REGION = "";//存储桶区域
    final static String BUCKET_URL = "https://***.cos.ap-guangzhou.myqcloud.com/";//访问域名
    final static String SECRET_ID = "";//secretId，需要在腾讯云申请
    final static String SECRET_KEY = "";//secretKey，需要在腾讯云申请
}
