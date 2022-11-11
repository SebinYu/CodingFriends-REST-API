package net.skhu.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class Participation {
    BigInteger participationRate_id;
    Integer studentId;
    Integer studygroupId;
    String studyGroup_Leader;
    Integer week;
    Boolean weeklyAttendance;
    private LocalDateTime updateDate;
}