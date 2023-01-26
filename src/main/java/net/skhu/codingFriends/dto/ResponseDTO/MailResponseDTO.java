package net.skhu.codingFriends.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.mail.SimpleMailMessage;

@Data
public class MailResponseDTO {

    private String To;
    private String From;
    private String Subject;
    private String Text;

}
