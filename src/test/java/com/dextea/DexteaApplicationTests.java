package com.dextea;

import com.dextea.Utils.COSUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DexteaApplicationTests {

    @Test
    void contextLoads() {
        COSUtils cosUtils = new COSUtils();
        System.out.println(cosUtils.getBucketList());
    }
}
