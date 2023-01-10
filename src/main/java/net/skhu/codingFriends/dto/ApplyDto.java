package net.skhu.codingFriends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.studygroup;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDto {
    private BigInteger apply_id;

    private String userId;
    private String studygroupId;
    private String title;
    private String applyStatus;
    private String application;
    private String name;
    private LocalDateTime updateDate;

    public static ApplyDto toDto(apply apply) {
        return new ApplyDto(
                apply.getApply_id(),
                apply.getUserId(),
                apply.getStudygroupId(),
                apply.getTitle(),
                apply.getApplyStatus(),
                apply.getApplication(),
                apply.getName(),
                apply.getUpdateDate());
    }
}