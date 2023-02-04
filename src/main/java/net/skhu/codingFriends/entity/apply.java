package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger apply_id;

    BigInteger userIdtemp;
    BigInteger studyIdTemp;
    String title;
    String applyStatus;
    String application;
    String name;
    LocalDateTime updateDate;
    Boolean mail_sent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    user user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studygroupId")
    @JsonIgnore
    studygroup studygroup;

}
