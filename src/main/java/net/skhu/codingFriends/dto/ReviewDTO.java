package net.skhu.codingFriends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDTO {
    BigInteger review_id;
    private BigInteger studentId;
    private BigInteger studygroupId;
    String studyGroupPartner;
    Double reviewScore;
    String reviewContents;
    Integer objection;
    LocalDateTime updateDate;
}
