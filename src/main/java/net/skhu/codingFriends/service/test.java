package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ApplyIdVO;
import net.skhu.codingFriends.dto.ResponseDTO.EventTicketResponseDTO;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import net.skhu.codingFriends.service.event.EventFacade;
import net.skhu.codingFriends.service.event.EventService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class test {
    private final EventFacade eventFacade;

    public void createEventTicketTest(Long eventId) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);


        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    try {
                        eventFacade.createEventTicketBroker(eventId);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }  finally {
                    countDownLatch.countDown();
                }
            });
        }

    }

}
