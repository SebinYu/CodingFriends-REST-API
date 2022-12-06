package net.skhu.dto.response;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class ResponseParticipation {
    BigInteger participationRate_id;

    String studentId;
    String studygroupId;
    String studyGroup_Leader;
    Integer week;
    String weeklyAttendance;
    String weeklyHomework;
    Double lectureScore;
    private LocalDateTime updateDate;
}