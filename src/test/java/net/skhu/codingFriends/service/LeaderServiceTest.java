//package net.skhu.codingFriends.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import net.skhu.codingFriends.VO.ApplyIdVO;
//import net.skhu.codingFriends.entity.apply;
//
//import net.skhu.codingFriends.entity.studygroup;
//import net.skhu.codingFriends.entity.user;
//import net.skhu.codingFriends.repository.UserRepository;
//import net.skhu.codingFriends.repository.apply.ApplyRepository;
//import net.skhu.codingFriends.repository.participation.ParticipationRepository;
//import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
//import org.hamcrest.CoreMatchers;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.web.reactive.server.JsonPathAssertions;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.assertj.core.api.Assertions.assertThat;
//import java.math.BigInteger;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import static org.junit.Assert.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class LeaderServiceTest {
//    LeaderService leaderService;
//
//    @Mock
//    UserService userService;
//    @Mock
//    ApplyRepository applyRepository;
//    @Mock
//    StudygroupRepository studygroupRepository;
//    @Mock
//    ParticipationRepository participationRepository;
//    @Mock
//    UserRepository userRepository;
//
//    MockMvc mockMvc;
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    void setup() {
//        this.leaderService = new LeaderService(applyRepository,studygroupRepository,participationRepository,userRepository);
//    }
//
//    @Test
//    public void testGetApplications() throws InterruptedException {
//        //given
//        ApplyIdVO applyIdVO = new ApplyIdVO();
//        applyIdVO.setApply_ids(new BigInteger[67]);
//        user user = new user(1, "아이디", "test123", "신청자 이름", "s@gmail.com", true, "ROLE_USER", false,"도로명","상세",null);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        CountDownLatch countDownLatch = new CountDownLatch(100);
//
//        //then
//        for (int i = 0; i < 100; i++) {
//            executorService.submit(() -> {
//                try {
//                    leaderService.accept(applyIdVO, user);
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//
//        countDownLatch.await();
//        net.skhu.codingFriends.entity.studygroup actual = studygroupRepository.findById(BigInteger.valueOf(56))
//                .orElseThrow();
//        int temp = actual.getCurrentNum();
//        assertThat(temp).isEqualTo("23");
//    }
//}