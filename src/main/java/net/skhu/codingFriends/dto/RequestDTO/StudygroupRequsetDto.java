package net.skhu.codingFriends.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.studygroup;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudygroupRequsetDto {
    String title;
    String content;
    int learningMaterial_id;
    String writer;
    int totalNum;
    int currentNum;
    LocalDate startDate;
    LocalDate endDate;
}
