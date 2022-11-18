package net.skhu.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class Apply {
    BigInteger apply_id;
    String userId;
    String studygroupId;
    String title;
    String application;
    String name;
    LocalDateTime updateDate;
}
