package com.chen.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chen.cn.dao")
public class SbShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbShiroApplication.class, args);
    }

}
