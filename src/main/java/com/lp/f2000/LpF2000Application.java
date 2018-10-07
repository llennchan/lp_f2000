package com.lp.f2000;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lp.f2000.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
@ComponentScan("com.lp.f2000")
public class LpF2000Application {

	public static void main(String[] args) {
		SpringApplication.run(LpF2000Application.class, args);
	}
}
