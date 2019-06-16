package com.hunmengwl.projectlove;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hunmengwl.projectlove.mapper")
public class ProjectloveApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectloveApplication.class, args);
	}

}
