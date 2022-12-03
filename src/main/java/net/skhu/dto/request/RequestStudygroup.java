package net.skhu.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RequestStudygroup implements Serializable {
    private static final long serialVersionUID = 1L;
    BigInteger studyGroup_id;

    @NotEmpty(message="학번을 입력하세요")
    String title;

    String content;
    int learningMaterial_id;
    String writer;
    int totalNum;
    int currentNum;
    LocalDateTime updateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
}
