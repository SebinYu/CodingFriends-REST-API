package net.skhu.codingFriends.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.apply;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequsetDto {
    private BigInteger apply_id;

    private int userId;
    private BigInteger studygroupId;
    private String title;
    private String applyStatus;
    private String application;
    private String name;
    private LocalDateTime updateDate;

}