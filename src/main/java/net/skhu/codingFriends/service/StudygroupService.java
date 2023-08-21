package net.skhu.codingFriends.service;

import net.skhu.codingFriends.dto.RequestDTO.ApplyRequsetDto;
import net.skhu.codingFriends.dto.RequestDTO.StudygroupRequsetDto;
import net.skhu.codingFriends.dto.ResponseDTO.ApplyResponseDto;
import net.skhu.codingFriends.dto.ResponseDTO.StudygroupResponseDto;
import net.skhu.codingFriends.entity.Learningmaterial;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;

import java.math.BigInteger;
import java.util.List;


public interface StudygroupService {

    List<StudygroupResponseDto> getStudygrouplist();
    StudygroupResponseDto getStudygroup(BigInteger studyGroup_id);
    StudygroupResponseDto createStudygroup(StudygroupRequsetDto studygroupRequsetDto, User user);

    StudygroupResponseDto updateStudygroup(Integer id, StudygroupRequsetDto studygroupRequsetDto);
    void deleteStudygroupById(int id);


    List<Studygroup> searchStudygroupWithKeyword(String keyword);

    List<Studygroup> searchStudygroupWithLearningMaterialIdAndKeyword(Integer learningMaterial_id, String keyword);
    List<Learningmaterial> findAllLearningMaterial();
    ApplyResponseDto applyStudygroup(ApplyRequsetDto applyRequsetDto, Long studyGroup_id, User user);
}