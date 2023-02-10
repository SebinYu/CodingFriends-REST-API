package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ApplyIdVO;
import net.skhu.codingFriends.VO.ParticipationVO;
import net.skhu.codingFriends.dto.RequestDTO.ParticipationRequsetDTO;
import net.skhu.codingFriends.dto.ResponseDTO.ApplyResponseDto;
import net.skhu.codingFriends.dto.ResponseDTO.ParticipationResponseDTO;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.enums.MyStatus;
import net.skhu.codingFriends.exception.ApplyInfoNotFoundException;
import net.skhu.codingFriends.exception.ParticipationFullException;
import net.skhu.codingFriends.exception.UncorrectStatusInputForm;
import net.skhu.codingFriends.exception.studygroup.StudygroupIdNotFound;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.apache.el.parser.Node;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class LeaderService {
    private final ApplyRepository applyRepository;
    private final StudygroupRepository studygroupRepository;
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<apply> getApplications(user user) {
        return applyRepository.findByUser(user);
    }
    @Transactional(readOnly = true)
    public List<studygroup> getStudygroups(user user) {
        return studygroupRepository.findByUserID(user);
    }


    @Transactional
    public List<ParticipationResponseDTO> accept(ApplyIdVO applyIdVO, user user) {
        int ApplyIdlength = applyIdVO.getApply_ids().length;
        List<ParticipationResponseDTO> participationrateList = new ArrayList<>();

        for(int i = 0; i< ApplyIdlength; i++){
            BigInteger[] applyIDs = applyIdVO.getApply_ids();
            BigInteger applyID = applyIDs[i];

            net.skhu.codingFriends.entity.participationrate participationrate =  new participationrate();
            List<apply> applyInfo = applyRepository.findByApplierID(applyID);
            if(applyInfo.size() != 0){
                studygroup studygroup = applyInfo.get(0).getStudygroup();

                int totalNum = studygroup.getTotalNum();
                int currentNum = studygroup.getCurrentNum();
                if(currentNum < totalNum){
                    studygroup.setCurrentNum(currentNum++);
                    studygroupRepository.updateCurrentNum(studygroup);

                    participationrate.setUser(applyInfo.get(0).getUser());
                    participationrate.setStudygroup(applyInfo.get(0).getStudygroup());
                    participationrate.setStudyGroup_Leader(user.getName());
                    participationrate.setWeek(0);

                    participationrate.setWeeklyAttendance(MyStatus.Undefined.value());
                    participationrate.setWeeklyHomework(MyStatus.Undefined.value());
                    participationrate.setUpdateDate(LocalDateTime.now());

                    participationRepository.save(participationrate);

                    //신청상태 - "등록"으로 변경
                    apply applyTemp = new apply();
                    applyTemp.setApply_id(applyInfo.get(0).getApply_id());
                    applyTemp.setApplyStatus(MyStatus.Accepted.value());
                    applyRepository.updateApplyStatus(applyTemp);

                    participationrateList.add(ParticipationResponseDTO.toDto(participationrate));
                } else{
                    throw new ParticipationFullException();
                }

            }else{
                throw new ApplyInfoNotFoundException();
            }

        }
        return participationrateList;

    }

    @Transactional
    public List<ApplyResponseDto> decline(ApplyIdVO applyIdVO) {
        int ApplyIdlength = applyIdVO.getApply_ids().length;
        List<ApplyResponseDto> declindedApplyList = new ArrayList<>();

        for(int i = 0; i< ApplyIdlength; i++){
            BigInteger[] applyIDs = applyIdVO.getApply_ids();
            BigInteger applyID = applyIDs[i];

            List<apply> applyTemp =  applyRepository.findByApplierID(applyID);
            declindedApplyList.add(ApplyResponseDto.toDto(applyTemp.get(0)));

            applyRepository.deleteById(applyID);
        }
        return declindedApplyList;
    }

    @Transactional(readOnly = true)
    public List<ApplyResponseDto> getParticipants(Long studygroup_id) {
        studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        List<apply> applyTemp = applyRepository.findByStudygroup(studygroupTemp);
        List<ApplyResponseDto> applyResponseDtos = new ArrayList<>();
        applyTemp.forEach(s -> applyResponseDtos.add(ApplyResponseDto.toDto(s)));
        return applyResponseDtos;
    }


    public participationrate setParticipationrateTemp(ParticipationRequsetDTO participationRequsetDTOTemp){

            participationrate participationrateTemp = new participationrate();

            //참여도 점수 계산
            Double lectureScore = 0.0;

            if(participationRequsetDTOTemp.getWeeklyAttendance() == MyStatus.Checked){
                lectureScore += 100.0;
            }

            if(participationRequsetDTOTemp.getWeeklyHomework() == MyStatus.Checked){
                lectureScore += 100.0;
            }

            //참여도 점수평균 - (출석+과제) / 2
            Double finalLectureScore = lectureScore/2;


            participationrateTemp.setStudyGroup_Leader(participationRequsetDTOTemp.getStudyGroup_Leader());
            participationrateTemp.setWeek(participationRequsetDTOTemp.getWeek());
            MyStatus tempAttendance = participationRequsetDTOTemp.getWeeklyAttendance();
            if(tempAttendance == null){
                throw new UncorrectStatusInputForm();
            }
            participationrateTemp.setWeeklyAttendance(tempAttendance.value());
            MyStatus tempHomework = participationRequsetDTOTemp.getWeeklyHomework();

            if(tempHomework == null){
                    throw new UncorrectStatusInputForm();
            }
            participationrateTemp.setWeeklyHomework(tempHomework.value());
            participationrateTemp.setLectureScore(finalLectureScore);
            participationrateTemp.setUpdateDate(LocalDateTime.now());


            Integer userID = participationRequsetDTOTemp.getStudentId();
            user userTemp = userRepository.findById(userID).orElseThrow(() -> {
                return new StudygroupIdNotFound();
            });
            participationrateTemp.setUser(userTemp);


            BigInteger studygroupID = participationRequsetDTOTemp.getStudygroupId();
            studygroup studygroupTemp = studygroupRepository.findById(studygroupID).orElseThrow(() -> {
                return new StudygroupIdNotFound();
            });
            participationrateTemp.setStudygroup(studygroupTemp);

        return participationrateTemp;
    }


    @Transactional
    public List<ParticipationResponseDTO> postAttendance(ParticipationVO participationVO) {

        //인덱스 별, 참여율 등록
        ParticipationRequsetDTO[] participationRequsetDTOS = participationVO.getParticipationRequsetDTOList();
        List<ParticipationResponseDTO> participationrateList = new ArrayList<>();

        for(int i = 0; i < participationRequsetDTOS.length; i++){
            ParticipationRequsetDTO participationRequsetDTOTemp = participationRequsetDTOS[i];

            participationrate participationrateTemp = setParticipationrateTemp(participationRequsetDTOTemp);

            participationRepository.save(participationrateTemp);

            participationrateList.add(ParticipationResponseDTO.toDto(participationrateTemp));
        }
        return participationrateList;
    }

    @Transactional(readOnly = true)
    public List<ParticipationResponseDTO> getAttendance(Long studygroup_id) {
        studygroup studygroupTemp = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        List<participationrate> participationTemp = participationRepository.findByStudygroup(studygroupTemp);
        List<ParticipationResponseDTO> participationResponseDTOs = new ArrayList<>();
        participationTemp.forEach(s -> participationResponseDTOs.add(ParticipationResponseDTO.toDto(s)));
        return participationResponseDTOs;
    }


}
