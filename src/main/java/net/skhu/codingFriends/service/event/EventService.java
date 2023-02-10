package net.skhu.codingFriends.service.event;

import net.skhu.codingFriends.dto.ResponseDTO.EventResponseDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventTicketResponseDTO;
import net.skhu.codingFriends.entity.event.event;
import net.skhu.codingFriends.entity.event.eventTicket;
import net.skhu.codingFriends.repository.event.EventRepository;
import net.skhu.codingFriends.repository.event.EventTicketRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventTicketRepository eventTicketRepository;

    public EventService(final EventRepository eventRepository, final EventTicketRepository eventTicketRepository) {
        this.eventRepository = eventRepository;
        this.eventTicketRepository = eventTicketRepository;
    }

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
    public EventTicketResponseDTO createEventTicket(final Long eventId) {
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
    }


}
