package com.goldendust.profile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.goldendust.profile")
public class MyProfileProject070524Application {

	public static void main(String[] args) {
		SpringApplication.run(MyProfileProject070524Application.class, args);
	}

}
