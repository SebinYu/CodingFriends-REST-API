package net.skhu.codingFriends.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
public class apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger apply_id;

    String userId;
    String studygroupId;
    String title;
    String applyStatus;
    String application;
    String name;
    LocalDateTime updateDate;
}
