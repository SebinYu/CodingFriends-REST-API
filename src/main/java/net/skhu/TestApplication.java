package net.skhu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
@EnableCaching
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"net.skhu.controller"})
@ComponentScan(basePackages = {"net.skhu.mapper"})
@ComponentScan(basePackages = {"net.skhu.service"})
@ComponentScan(basePackages = {"net.skhu.Persistence"})
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
