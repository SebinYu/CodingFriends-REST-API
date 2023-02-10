package net.skhu.codingFriends.dto.ResponseDTO;

import lombok.Data;
import net.skhu.codingFriends.entity.event.event;

import javax.persistence.*;

@Data
public class EventTicketResponseDTO {
    private Long id;
    private net.skhu.codingFriends.entity.event.event event;
}
