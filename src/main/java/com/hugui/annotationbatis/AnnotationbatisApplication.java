package com.hugui.annotationbatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.hugui.annotationbatis.mapper*")
@EnableCaching
@EnableAspectJAutoProxy
public class AnnotationbatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotationbatisApplication.class, args);
	}
}
