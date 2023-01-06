package net.skhu.codingFriends.service.impl;

import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.LearningmaterialRepository;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudygroupServiceImpl implements StudygroupService {
    @Autowired
    public StudygroupRepository studygroupRepository;


    @Autowired
    public LearningmaterialRepository learningmaterialRepository;

    // 전체 게시물 조회
    @Transactional(readOnly = true)
    public List<StudygroupDto> getStudygroups() {
        List<studygroup> studygroups = studygroupRepository.findAll();
        List<StudygroupDto> StudygroupDtos = new ArrayList<>();
        studygroups.forEach(s -> StudygroupDtos.add(StudygroupDto.toDto(s)));
        return StudygroupDtos;
    }

    // 개별 게시물 조회
    @Transactional(readOnly = true)
    public StudygroupDto getStudygroup(BigInteger id) {
        studygroup studygroup = studygroupRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Studygroup Id를 찾을 수 없습니다.");
        });
        StudygroupDto studygroupDto = StudygroupDto.toDto(studygroup);
        return studygroupDto;
    }


    // 게시물 작성
    @Transactional
    public StudygroupDto write(StudygroupDto studygroupDto, user user) {
        studygroup studygroup = new studygroup();
        studygroup.setTitle(studygroupDto.getTitle());
        studygroup.setContent(studygroupDto.getContent());
        studygroup.setLearningMaterial_id(studygroupDto.getLearningMaterial_id());
        studygroup.setWriter(user.getName());
        studygroup.setX_map(studygroupDto.getX_map());
        studygroup.setY_map(studygroupDto.getY_map());
        studygroup.setTotalNum(studygroupDto.getTotalNum());
        studygroup.setCurrentNum(studygroupDto.getCurrentNum());
        studygroup.setStartDate(studygroupDto.getStartDate());
        studygroup.setEndDate(studygroupDto.getEndDate());

        studygroup.setUser(user);

        studygroupRepository.save(studygroup);
        return StudygroupDto.toDto(studygroup);
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


    // 게시물 작성
    @Transactional
    public void insert(studygroup studygroup) {
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

    public void deleteByStudyGroup_id(int id){
        studygroupRepository.deleteById(BigInteger.valueOf(id));
    }

    public List<studygroup> updateDate(){
        return studygroupRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDate")) ;
    }
}