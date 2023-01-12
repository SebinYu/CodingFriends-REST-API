package net.skhu.codingFriends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.entity.participationrate;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDTO {
    BigInteger participationRate_id;

    private int studentId;
    private BigInteger studygroupId;
    String studyGroup_Leader;
    Integer week;
    String weeklyAttendance;
    String weeklyHomework;
    Double lectureScore;
    private LocalDateTime updateDate;

    public static ParticipationDTO toDto(participationrate participationrate) {
        return new ParticipationDTO(
                participationrate.getParticipationRate_id(),
                participationrate.getUser().getUser_id(),
                participationrate.getStudygroup().getStudyGroup_id(),
                participationrate.getStudyGroup_Leader(),
                participationrate.getWeek(),
                participationrate.getWeeklyAttendance(),
                participationrate.getWeeklyHomework(),
                participationrate.getLectureScore(),
                participationrate.getUpdateDate()
        );
    }

}
