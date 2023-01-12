package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
public class review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger review_id;

    String studyGroupPartner;
    Double reviewScore;
    String reviewContents;
    Double lectureScore;
    Integer objection;
    LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    @JsonIgnore
    user user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studygroupId")
    @JsonIgnore
    studygroup studygroup;

}
