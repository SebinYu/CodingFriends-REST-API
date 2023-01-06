package net.skhu.codingFriends.service;

import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.List;


public interface StudygroupService {

    List<StudygroupDto> getStudygroups();
    StudygroupDto getStudygroup(BigInteger studyGroup_id);
    StudygroupDto write(StudygroupDto studygroupDto, user user);

    StudygroupDto update(Integer id, StudygroupDto studygroupDto);
    void deleteByStudyGroup_id(int id);


    List<studygroup> searchWithKeyword(String keyword);

    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);
    List<learningmaterial> findAllLearningMaterial();


}