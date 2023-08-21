package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="applyStudygroup")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger apply_id;

    String title;
    String applyStatus;
    String application;
    String name;

    @UpdateTimestamp
    LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studygroupId")
    @JsonIgnore
    Studygroup studygroup;

}
