package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.AcceptedApplyIdVO;
import net.skhu.codingFriends.dto.ApplyDto;
import net.skhu.codingFriends.dto.ParticipationDTO;
import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaderService {
    private final ApplyRepository applyRepository;
    private final StudygroupRepository studygroupRepository;
    private final ParticipationRepository participationRepository;

    @Transactional
    public List<ParticipationDTO> accept(AcceptedApplyIdVO acceptedApplyIdVO, user user) {
        int ApplyIdlength = acceptedApplyIdVO.getApply_id().length;
        List<ParticipationDTO> participationrateList = new ArrayList<>();

        for(int i = 0; i< ApplyIdlength; i++){
            BigInteger[] applyIDs = acceptedApplyIdVO.getApply_id();
            BigInteger applyID = applyIDs[i];

            net.skhu.codingFriends.entity.participationrate participationrate =  new participationrate();
            List<apply> applyInfo = applyRepository.findByApplierID(applyID);
            participationrate.setUser(applyInfo.get(0).getUser());
            participationrate.setStudygroup(applyInfo.get(0).getStudygroup());
            participationrate.setStudyGroup_Leader(user.getName());
            participationrate.setWeek(0);
            participationrate.setWeeklyAttendance("미정");
            participationrate.setWeeklyHomework("미정");

            participationRepository.save(participationrate);

            participationrateList.add(ParticipationDTO.toDto(participationrate));
        }
        return participationrateList;

    }
    @Transactional
    public List<apply> getApplications(user user) {
        return applyRepository.findByUser(user);
    }
    @Transactional
    public List<studygroup> getStudygroups(user user) {
        return studygroupRepository.findByUserID(user);
    }
}
