package net.skhu.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Studygroup implements Serializable {
    private static final long serialVersionUID = 1L;
    BigInteger studyGroup_id;
    String title;
    String content;
    int learningMaterial_id;
    String writer;
    int totalNum;
    int currentNum;
    private LocalDateTime updateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
}
