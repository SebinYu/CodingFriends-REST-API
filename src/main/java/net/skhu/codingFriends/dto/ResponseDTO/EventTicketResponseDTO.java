package net.skhu.codingFriends.dto.ResponseDTO;

import lombok.Data;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.entity.event.Event;

@Data
public class EventTicketResponseDTO {
    private Long id;
    User user;
    private Event event;
}
