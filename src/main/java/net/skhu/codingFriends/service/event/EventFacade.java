package net.skhu.codingFriends.service.event;

import net.skhu.codingFriends.entity.event.event;
import net.skhu.codingFriends.entity.event.eventTicket;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.event.EventRepository;
import net.skhu.codingFriends.repository.event.EventTicketRepository;
import net.skhu.codingFriends.repository.event.RedisLockRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EventFacade {
    EventService eventService;

    private final RedissonClient redissonClient;

    public EventFacade(final EventService eventService, final RedissonClient redissonClient) {
        this.eventService = eventService;
        this.redissonClient = redissonClient;
    }
    //Redisson 방식
    public void createEventTicketBroker(final Long eventId, user user) throws InterruptedException {
        RLock lock = redissonClient.getLock(String.valueOf(eventId));
//        RLock lock = Redisson.create().getLock(String.valueOf(eventId));

        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            eventService.createEventTicketForFacade(eventId, user);
            /* 비즈니스 로직 */
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
