package net.skhu.codingFriends.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.config.redis.RedisConfiguration;
import net.skhu.codingFriends.dto.RequestDTO.EventRequestDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventResponseDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventTicketResponseDTO;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.event.EventService;
import net.skhu.codingFriends.service.test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static net.skhu.codingFriends.response.Response.success;

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
    public Response createEventTicket(@PathVariable final Long eventId, Authentication authentication) throws InterruptedException {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();

//        EventTicketResponseDTO response = eventService.createEventTicket(eventId, user);
        return success(eventService.createEventTicketForFacade(eventId, user),"/studygroup/create");
    }


    @PostMapping("/{eventId}/ticketsTest")
    public void ticketsTest(@PathVariable final Long eventId, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();

        test.createEventTicketTest(eventId, user);
    }
}
