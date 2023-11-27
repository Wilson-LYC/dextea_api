package com.dextea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = {"com.dextea.mapper"})
public class DexteaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DexteaApplication.class, args);
    }
}
