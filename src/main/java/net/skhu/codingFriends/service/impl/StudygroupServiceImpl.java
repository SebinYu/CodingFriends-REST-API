package net.skhu.codingFriends.service.impl;

import net.skhu.codingFriends.dto.ActionResult;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.LearningmaterialRepository;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void insert(studygroup studygroup) throws Exception {
        if (studygroupRepository.findByStudyGroup_id(BigInteger.valueOf(-1)) != null)
            throw new Exception("예외처리 연습");
        studygroupRepository.save(studygroup);
    }

    public void update(studygroup studygroupInfo) {

        BigInteger studyGroup_id= studygroupInfo.getStudyGroup_id();
        String title = studygroupInfo.getTitle();
        String content = studygroupInfo.getContent();
        int learningMaterial_id = studygroupInfo.getLearningMaterial_id();
        String writer = studygroupInfo.getWriter();
        Double x_map = studygroupInfo.getX_map();
        Double y_map = studygroupInfo.getY_map();
        int totalNum = studygroupInfo.getTotalNum();
        int currentNum = studygroupInfo.getCurrentNum();
        LocalDate startDate = studygroupInfo.getStartDate();
        LocalDate endDate = studygroupInfo.getEndDate();

         studygroupRepository.update(
                 studyGroup_id,
                title,
                content,
                learningMaterial_id,
                writer,
                x_map,
                y_map,
                totalNum,
                currentNum,
                startDate,
                endDate);

    }
    }