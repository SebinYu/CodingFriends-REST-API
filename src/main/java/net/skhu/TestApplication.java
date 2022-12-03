package net.skhu;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableEncryptableProperties
@ComponentScan(basePackages = {"net.skhu.controller"})
@ComponentScan(basePackages = {"net.skhu.mapper"})
@ComponentScan(basePackages = {"net.skhu.service"})
@ComponentScan(basePackages = {"net.skhu.config"})
@ComponentScan(basePackages = {"net.skhu.repository"})
public class TestApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestApplication.class);
	}
	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);


	}

}
