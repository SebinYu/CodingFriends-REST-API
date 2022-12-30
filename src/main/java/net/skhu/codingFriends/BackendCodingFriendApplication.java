package net.skhu.codingFriends;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class BackendCodingFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCodingFriendApplication.class, args);
	}

}
