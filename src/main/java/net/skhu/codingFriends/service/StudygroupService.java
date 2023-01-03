package net.skhu.codingFriends.service;

import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.StudygroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;


public interface StudygroupService {

    List<studygroup> findAll();

    studygroup findOneStudygroupInfo(BigInteger studyGroup_id);

//    List<studygroup> searchWithLearningMaterial_id(BigInteger learningMaterial_id);
    List<studygroup> searchWithKeyword(String keyword);

    List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword);

}