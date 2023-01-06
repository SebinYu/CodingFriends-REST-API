package net.skhu.codingFriends.service;

import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.List;


public interface StudygroupService {

    List<StudygroupDto> getStudygroups();

    studygroup findOneStudygroupInfo(BigInteger studyGroup_id);

//    List<studygroup> searchWithLearningMaterial_id(BigInteger learningMaterial_id);
    List<studygroup> searchWithKeyword(String keyword);

    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);
    List<learningmaterial> findAllLearningMaterial();
    void insert(studygroup studygroupInfo);

    void update(@RequestBody studygroup studygroupInfo);
    void deleteByStudyGroup_id(int id);

}