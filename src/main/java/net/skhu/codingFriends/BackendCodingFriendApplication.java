package net.skhu.codingFriends;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {"net.skhu.codingFriends.controller"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.repository"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.service"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.config"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.batch"})
@EnableEncryptableProperties
public class BackendCodingFriendApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendCodingFriendApplication.class, args);
	}

}
