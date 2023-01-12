package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ParticipationDTO;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.studygroup.StudygroupIdNotFound;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.review.ReviewRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ParticipationRepository participationRepository;
    private final StudygroupRepository studygroupRepository;

    @Transactional
    public List<ParticipationDTO> getEXColleague(Long studygroup_id, user myUserInfo) {
        studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });


        List<participationrate> participationrateList = participationRepository.findByStudygroupAndStatus(studygroupTemp);
        for(int i = 0; i<participationrateList.size(); i++){
            if(participationrateList.get(i).getUser() == myUserInfo){
                participationrateList.remove(myUserInfo);
            }
        }
        List<ParticipationDTO> ParticipationDTOs = new ArrayList<>();
        participationrateList.forEach(s -> ParticipationDTOs.add(ParticipationDTO.toDto(s)));
        return ParticipationDTOs;
    }
}
