package net.skhu.codingFriends.service.impl;

import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudygroupServiceImpl implements StudygroupService {

    @Autowired
    public StudygroupRepository studygroupRepository;

    public List<studygroup> findAll(){
        return studygroupRepository.findAll();
    }

    public studygroup findOneStudygroupInfo(BigInteger studyGroup_id){
        return studygroupRepository.findByStudyGroup_id(studyGroup_id);
    }

//    public List<studygroup> searchWithLearningMaterial_id(BigInteger learningMaterial_id){
//        return studygroupRepository.searchWithLearningMaterial_id(learningMaterial_id);
//    }

    public List<studygroup> searchWithKeyword(String keyword){
        return studygroupRepository.findByTitleContaining(keyword);
    }

    public List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword){
        return studygroupRepository.searchWithLearningMaterial_idAndKeyword(learningMaterial_id, keyword);
    }

}