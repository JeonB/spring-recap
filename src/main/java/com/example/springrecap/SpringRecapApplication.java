package com.example.springrecap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRecapApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringRecapApplication.class, args);
        String lover = "고말숙";
        System.out.println(lover + " 사랑해");
    }
}
