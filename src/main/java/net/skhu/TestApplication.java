package net.skhu;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
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
@ComponentScan(basePackages = {"net.skhu.config"})
@ComponentScan(basePackages = {"net.skhu.repository"})
public class TestApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setPoolSize(2);
		encryptor.setPassword("password");
		encryptor.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");

		String plainText = "ssr_lc";
		String encryptedText = encryptor.encrypt(plainText);
		String decryptedText = encryptor.decrypt(encryptedText);
		System.out.println("Enc = " + encryptedText);
		System.out.println("Dec = " + decryptedText);
	}

}
