package net.skhu.codingFriends.VO;

import lombok.Data;
import net.skhu.codingFriends.dto.RequestDTO.ParticipationRequsetDTO;

@Data
public class ParticipationVO {
    ParticipationRequsetDTO[] participationRequsetDTOList;
}
