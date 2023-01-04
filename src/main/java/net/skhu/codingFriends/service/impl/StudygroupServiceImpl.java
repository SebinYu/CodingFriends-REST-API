package net.skhu.codingFriends.service.impl;

import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.LearningmaterialRepository;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class StudygroupServiceImpl implements StudygroupService {

    @Autowired
    public StudygroupRepository studygroupRepository;

    @Autowired
    public LearningmaterialRepository learningmaterialRepository;

    public List<studygroup> findAll(){
        return studygroupRepository.findAll();
    }

    public studygroup findOneStudygroupInfo(BigInteger studyGroup_id){
        return studygroupRepository.findByStudyGroup_id(studyGroup_id);
    }


    public List<studygroup> searchWithKeyword(String keyword){
        return studygroupRepository.findByTitleContaining(keyword);
    }

    public List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword){
        return studygroupRepository.searchWithLearningMaterial_idAndKeyword(learningMaterial_id, keyword);
    }

    public List<learningmaterial> findAllLearningMaterial(){
        return learningmaterialRepository.findAllLearningMaterial();
    }

    public studygroup save(studygroup studygroupInfo){
        return studygroupRepository.save(studygroupInfo);
    }


}