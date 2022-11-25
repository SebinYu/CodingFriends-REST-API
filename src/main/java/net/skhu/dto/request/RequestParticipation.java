package net.skhu.dto.request;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class RequestParticipation {
    BigInteger participationRate_id;

    String studentId;
    String studygroupId;
    String studyGroup_Leader;
    Integer week;
    String weeklyAttendance;
    String weeklyHomework;
    private LocalDateTime updateDate;
}