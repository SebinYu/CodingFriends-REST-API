package net.skhu.codingFriends.entity;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class studygroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger studyGroup_id;

    String title;
    String content;
    int learningMaterial_id;
    String writer;
    Double x_map;
    Double y_map;
    int totalNum;
    int currentNum;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    user user;
}
