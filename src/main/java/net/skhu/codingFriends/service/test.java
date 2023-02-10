package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ApplyIdVO;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class test {

    private final ApplyRepository applyRepository;
    private final StudygroupRepository studygroupRepository;
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final LeaderService leaderService;

    public Object acceptTest(user user) {
        ApplyIdVO applyIdVO = new ApplyIdVO();
        applyIdVO.setApply_ids(new BigInteger[]{BigInteger.valueOf(67), BigInteger.valueOf(69)});

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);


        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    leaderService.accept(applyIdVO, user);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        return null;
    }

}
