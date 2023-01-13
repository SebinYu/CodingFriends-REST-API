package net.skhu.codingFriends.service;

import net.skhu.codingFriends.dto.RequestDTO.ApplyRequsetDto;
import net.skhu.codingFriends.dto.RequestDTO.StudygroupRequsetDto;
import net.skhu.codingFriends.dto.ResponseDTO.ApplyResponseDto;
import net.skhu.codingFriends.dto.ResponseDTO.StudygroupResponseDto;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;

import java.math.BigInteger;
import java.util.List;


public interface StudygroupService {

    List<StudygroupResponseDto> getStudygroups();
    StudygroupResponseDto getStudygroup(BigInteger studyGroup_id);
    StudygroupResponseDto write(StudygroupRequsetDto studygroupRequsetDto, user user);

    StudygroupResponseDto update(Integer id, StudygroupRequsetDto studygroupRequsetDto);
    void deleteByStudyGroup_id(int id);


    List<studygroup> searchWithKeyword(String keyword);

    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);
    List<learningmaterial> findAllLearningMaterial();
    ApplyResponseDto apply(ApplyRequsetDto applyRequsetDto, Long studyGroup_id, user user);
}