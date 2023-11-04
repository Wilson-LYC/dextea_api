package com.dextea;

import com.dextea.mapper.StoreMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DexteaApplicationTests {
    @Autowired
    StoreMapper storeMapper;
    @Test
    void contextLoads(){
        System.out.println(storeMapper.getAll().toString());
    }

}
