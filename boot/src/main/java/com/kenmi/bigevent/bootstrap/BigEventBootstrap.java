package com.kenmi.bigevent.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.kenmi.bigevent.dal.dao")
@SpringBootApplication(scanBasePackages = "com.kenmi.bigevent")
public class BigEventBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(BigEventBootstrap.class, args);
    }

}
