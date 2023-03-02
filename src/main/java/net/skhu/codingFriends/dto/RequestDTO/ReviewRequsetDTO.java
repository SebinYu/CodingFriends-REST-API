package net.skhu.codingFriends.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequsetDTO {
    private BigInteger studentId;
    private BigInteger studygroupId;
    String studyGroupPartner;
    Double reviewScore;
    String reviewContents;
}
