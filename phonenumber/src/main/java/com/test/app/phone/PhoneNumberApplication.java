package com.test.app.phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={
        "com.test.app.phone"})
@EnableJpaRepositories
public class PhoneNumberApplication {
    public static void main(String[]args){
        SpringApplication.run(PhoneNumberApplication.class,args);
    }
}
