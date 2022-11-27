package net.skhu.dto;


import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class Review {

    BigInteger review_id;

    BigInteger studentId;
    BigInteger studygroupId;
    String studyGroupPartner;
    Double reviewScore;
    String reviewContents;
    Double lectureScore;
    boolean objection;
    LocalDateTime updateDate;

//    @ManyToOne
//    @JoinColumn(name = "studentId")
//    user user;
//
//    @ManyToOne
//    @JoinColumn(name = "studygroupId")
//    RequestStudygroup studygroup;
}
