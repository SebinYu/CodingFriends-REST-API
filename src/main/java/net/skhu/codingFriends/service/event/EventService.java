package net.skhu.codingFriends.service.event;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ResponseDTO.EventResponseDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventTicketResponseDTO;
import net.skhu.codingFriends.entity.event.event;
import net.skhu.codingFriends.entity.event.eventTicket;
import net.skhu.codingFriends.repository.RedisLockRepository;
import net.skhu.codingFriends.repository.event.EventRepository;
import net.skhu.codingFriends.repository.event.EventTicketRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventTicketRepository eventTicketRepository;
    private final RedisLockRepository redisLockRepository;



    @Transactional
    public EventResponseDTO createEvent(final Long ticketLimit) {
        event event = new event();
        event.setTicketLimit(ticketLimit);
        event savedEvent = eventRepository.save(event);
        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setId(savedEvent.getId());
        eventResponseDTO.setTicketLimit(savedEvent.getTicketLimit());
        return eventResponseDTO;
    }

    @Transactional
    public EventTicketResponseDTO createEventTicket(final Long eventId) throws InterruptedException {
        while (!redisLockRepository.lock(eventId)) {
            Thread.sleep(100);
        } // 락을 획득하기 위해 대기

        try {
            event event = eventRepository.findById(eventId).orElseThrow();
            if (event.isClosed()) {
                throw new RuntimeException("마감 되었습니다.");
            }

            eventTicket eventTicket = new eventTicket();
            eventTicket.setEvent(event);
            eventTicket savedEventTicket = eventTicketRepository.save(eventTicket);

            EventTicketResponseDTO eventTicketResponseDTO = new EventTicketResponseDTO();
            eventTicketResponseDTO.setId(savedEventTicket.getId());
            eventTicketResponseDTO.setEvent(savedEventTicket.getEvent());
            return eventTicketResponseDTO;
        } finally {
            redisLockRepository.unlock(eventId);
            // 락 해제
        }
    }


}
