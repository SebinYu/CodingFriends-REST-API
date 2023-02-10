package net.skhu.codingFriends.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.studygroup;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudygroupResponseDto implements Serializable {
    BigInteger studyGroup_id;

    String title;
    String content;
    int learningMaterial_id;
    String writer;
    int totalNum;
    int currentNum;
    private LocalDateTime updateDate;
    LocalDate startDate;
    LocalDate endDate;

    public static StudygroupResponseDto toDto(studygroup studygroup) {
        return new StudygroupResponseDto(
                studygroup.getStudyGroup_id(),
                studygroup.getTitle(),
                studygroup.getContent(),
                studygroup.getLearningMaterial_id(),
                //writer -> user테이블에서 직접 조회
                studygroup.getUser().getName(),
                studygroup.getTotalNum(),
                studygroup.getCurrentNum(),
                studygroup.getUpdateDate(),
                studygroup.getStartDate(),
                studygroup.getEndDate());
    }
}
