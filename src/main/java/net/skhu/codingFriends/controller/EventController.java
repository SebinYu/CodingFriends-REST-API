package net.skhu.codingFriends.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.dto.RequestDTO.EventRequestDTO;
import net.skhu.codingFriends.dto.ResponseDTO.EventResponseDTO;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.event.EventService;
import net.skhu.codingFriends.service.test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static net.skhu.codingFriends.response.Response.success;

@Tag(name = "선착순 이벤트", description = "선착순 이벤트 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final test test;

    @Operation(summary = "선착순 이벤트 생성", description = "선착순 이벤트 생성한다.")
    @PostMapping("/createEvent")
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO request) {
        Long ticketLimit = request.getTicketLimit();
        EventResponseDTO response = eventService.createEvent(ticketLimit);

        return ResponseEntity
                .created(URI.create("/events/" + response.getId()))
                .body(response);
    }

    @Operation(summary = "선착순 이벤트 신청", description = "선착순 이벤트 신청한다.")
    @PostMapping("/{eventId}/tickets")
    public Response createEventTicket(@PathVariable final Long eventId, Authentication authentication) throws InterruptedException {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();

//        EventTicketResponseDTO response = eventService.createEventTicket(eventId, User);
        return success(eventService.createEventTicketForFacade(eventId, user),"/studygroup/create");
    }


//    @PostMapping("/{eventId}/ticketsTest")
//    public void ticketsTest(@PathVariable final Long eventId, Authentication authentication) {
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        User User = principalDetails.getUser();
//
//        test.createEventTicketTest(eventId, User);
//    }
}
