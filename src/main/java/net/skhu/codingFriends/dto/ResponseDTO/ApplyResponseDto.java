package net.skhu.codingFriends.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.Apply;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResponseDto {
    private BigInteger apply_id;

    private int userId;
    private BigInteger studygroupId;
    private String title;
    private String applyStatus;
    private String application;
    private String name;
    private LocalDateTime updateDate;

    public static ApplyResponseDto toDto(Apply apply) {
        return new ApplyResponseDto(
                apply.getApply_id(),
                apply.getUser().getUser_id(),
                apply.getStudygroup().getStudyGroup_id(),
                apply.getTitle(),
                apply.getApplyStatus(),
                apply.getApplication(),
                apply.getName(),
                apply.getUpdateDate());
    }
}
