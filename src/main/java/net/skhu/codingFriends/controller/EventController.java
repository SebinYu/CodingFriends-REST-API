package net.skhu.codingFriends.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RequestDTO.EventRequestDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventResponseDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventTicketResponseDTO;
import net.skhu.codingFriends.service.event.EventService;
import net.skhu.codingFriends.service.test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final test test;

    @PostMapping("/createEvent")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO request) {
        Long ticketLimit = request.getTicketLimit();
        EventResponseDTO response = eventService.createEvent(ticketLimit);

        return ResponseEntity
                .created(URI.create("/events/" + response.getId()))
                .body(response);
    }

    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<EventTicketResponseDTO> createEventTicket(@PathVariable final Long eventId) throws InterruptedException {
        EventTicketResponseDTO response = eventService.createEventTicket(eventId);

        return ResponseEntity
                .created(URI.create("/events/" + response.getEvent().getId() + "/" + response.getId()))
                .body(response);
    }

    @PostMapping("/{eventId}/ticketsTest")
    public void ticketsTest(@PathVariable final Long eventId) {
        test.createEventTicketTest(eventId);
    }
}
