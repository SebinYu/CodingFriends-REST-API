package net.skhu.codingFriends;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"net.skhu.codingFriends.controller"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.repository"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.service"})
@ComponentScan(basePackages = {"net.skhu.codingFriends.config"})
@EnableEncryptableProperties
@EnableRedisHttpSession
@EnableCaching
@EnableRetry
public class BackendCodingFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCodingFriendApplication.class, args);
	}

}
