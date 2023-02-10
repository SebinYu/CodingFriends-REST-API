package net.skhu.codingFriends.controller;

import net.skhu.codingFriends.dto.RequestDTO.EventRequestDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventResponseDTO;
import net.skhu.codingFriends.service.event.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(final EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO request) {
        Long ticketLimit = request.getTicketLimit();
        EventResponseDTO response = eventService.createEvent(ticketLimit);

        return ResponseEntity
                .created(URI.create("/events/" + response.getId()))
                .body(response);
    }

//    @PostMapping("/{eventId}/tickets")
//    public ResponseEntity<EventTicketResponse> createEventTicket(@PathVariable final Long eventId) {
//        EventTicketResponse response = eventService.createEventTicket(eventId);
//
//        return ResponseEntity
//                .created(URI.create("/events/" + response.getEventId() + "/" + response.getId()))
//                .body(response);
//    }
}
