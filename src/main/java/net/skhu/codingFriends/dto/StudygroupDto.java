package net.skhu.codingFriends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.studygroup;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudygroupDto {
    BigInteger studyGroup_id;

    String title;
    String content;
    int learningMaterial_id;
    String writer;
    Double x_map;
    Double y_map;
    int totalNum;
    int currentNum;
    private LocalDateTime updateDate;
    LocalDate startDate;
    LocalDate endDate;

    public static StudygroupDto toDto(studygroup studygroup) {
        return new StudygroupDto(
                studygroup.getStudyGroup_id(),
                studygroup.getTitle(),
                studygroup.getContent(),
                studygroup.getLearningMaterial_id(),
                //writer -> user테이블에서 직접 조회
                studygroup.getUser().getName(),
                studygroup.getX_map(),
                studygroup.getY_map(),
                studygroup.getTotalNum(),
                studygroup.getCurrentNum(),
                studygroup.getUpdateDate(),
                studygroup.getStartDate(),
                studygroup.getEndDate());
    }
}
