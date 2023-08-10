package net.skhu.codingFriends.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skhu.codingFriends.enums.MyStatus;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequsetDTO {


    private int studentId;
    private BigInteger studygroupId;
    String studyGroup_Leader;
    Integer week;
    MyStatus weeklyAttendance;
    MyStatus weeklyHomework;
}
