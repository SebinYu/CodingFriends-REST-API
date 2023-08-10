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

    List<StudygroupResponseDto> getStudygroups();
    StudygroupResponseDto getStudygroup(BigInteger studyGroup_id);
    StudygroupResponseDto write(StudygroupRequsetDto studygroupRequsetDto, User user);

    StudygroupResponseDto update(Integer id, StudygroupRequsetDto studygroupRequsetDto);
    void deleteByStudyGroup_id(int id);


    List<Studygroup> searchWithKeyword(String keyword);

    List<Studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);
    List<Learningmaterial> findAllLearningMaterial();
    ApplyResponseDto apply(ApplyRequsetDto applyRequsetDto, Long studyGroup_id, User user);
}