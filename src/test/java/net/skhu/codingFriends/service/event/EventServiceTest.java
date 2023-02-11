//package net.skhu.codingFriends.service.event;
//
//import net.skhu.codingFriends.entity.event.event;
//import net.skhu.codingFriends.entity.event.eventTicket;
//import net.skhu.codingFriends.repository.event.RedisLockRepository;
//import net.skhu.codingFriends.repository.event.EventRepository;
//import net.skhu.codingFriends.repository.event.EventTicketRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(MockitoExtension.class)
//class EventServiceTest {
//    @InjectMocks
//    EventService eventService;
//    @Mock
//    EventTicketRepository eventTicketRepository;
//
//    @Mock
//    EventRepository eventRepository;
//
//
//    @Test
//    void createEventTicket() throws InterruptedException {
//
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        CountDownLatch countDownLatch = new CountDownLatch(100);
//
//        for (int i = 0; i < 100; i++) {
//            executorService.submit(() -> {
//                try {
//                    eventService.createEventTicket(1L);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//
//        countDownLatch.await();
//        Optional<net.skhu.codingFriends.entity.event.event> event2 = eventRepository.findById(1L);
//        List<eventTicket> actual = eventTicketRepository.findByEvent_id(event2);
//
//        assertThat(actual.size()).isEqualTo(30);
//    }
//}