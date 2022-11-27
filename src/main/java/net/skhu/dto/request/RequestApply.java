package net.skhu.dto.request;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class RequestApply {
    BigInteger apply_id;
    String userId;
    String studygroupId;
    String title;
    String applyStatus;
    String application;
    String name;
    LocalDateTime updateDate;
}
