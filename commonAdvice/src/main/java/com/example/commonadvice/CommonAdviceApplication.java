package com.example.commonadvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CommonAdviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonAdviceApplication.class, args);
    }

}
