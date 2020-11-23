package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/jt/com.jt.mapper")
public class springbootRun {
    public static void main(String[] args) {
        SpringApplication.run(springbootRun.class, args);
    }
}
