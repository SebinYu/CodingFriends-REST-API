package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="participationrate")
public class Participationrate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger participationRate_id;

    String studyGroup_Leader;
    Integer week;
    String weeklyAttendance;
    String weeklyHomework;
    Double lectureScore;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    @JsonIgnore
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studygroupId")
    @JsonIgnore
    Studygroup studygroup;
}
