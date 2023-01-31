package net.skhu.codingFriends.repository;

import net.skhu.codingFriends.entity.user;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }


    @Test
    void findByUsername() {

        //given

        userRepository.save(user.builder()
                        .user_id(1)
                        .username("name")
                        .password("psw")
                        .name("n")
                        .email("s@gmail.com")
                        .enabled(true)
                        .userType("d")
                        .address("주소")
                        .address_detail("상세주소")
                .build());

        //when
        user postsList = userRepository.findByUsername("name");
    }

    @Test
    void existsByUsername() {
    }
}