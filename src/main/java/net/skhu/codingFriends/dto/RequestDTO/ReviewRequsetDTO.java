package net.skhu.codingFriends.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewRequsetDTO {
    private BigInteger studentId;
    private BigInteger studygroupId;
    String studyGroupPartner;
    Double reviewScore;
    String reviewContents;
    Integer objection;
}
