package net.skhu.codingFriends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"net.skhu.codingFriends.controller"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.repository"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.service"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.config"})
public class BackendCodingFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCodingFriendApplication.class, args);
	}

}
