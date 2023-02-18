package net.skhu.codingFriends.dto.ResponseDTO;

import lombok.Data;
import net.skhu.codingFriends.entity.event.event;
import net.skhu.codingFriends.entity.user;

import javax.persistence.*;

@Data
public class EventTicketResponseDTO {
    private Long id;
    user user;
    private net.skhu.codingFriends.entity.event.event event;
}
