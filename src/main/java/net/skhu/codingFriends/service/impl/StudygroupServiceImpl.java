package net.skhu.codingFriends.service.impl;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.LearningmaterialRepository;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudygroupServiceImpl implements StudygroupService {

    public final StudygroupRepository studygroupRepository;

    private final UserRepository userRepository;

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

    // 게시물 수정
    @Transactional
    public StudygroupDto update(Integer id, StudygroupDto studygroupDto) {
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(id)).orElseThrow(() -> {
            return new IllegalArgumentException("studygroup Id를 찾을 수 없습니다!");
        });

        studygroup.setStudyGroup_id(BigInteger.valueOf(id));
        studygroup.setTitle(studygroupDto.getTitle());
        studygroup.setContent(studygroupDto.getContent());
        studygroup.setLearningMaterial_id(studygroupDto.getLearningMaterial_id());
        studygroup.setX_map(studygroupDto.getX_map());
        studygroup.setY_map(studygroupDto.getY_map());
        studygroup.setTotalNum(studygroupDto.getTotalNum());
        studygroup.setCurrentNum(studygroupDto.getCurrentNum());
        studygroup.setStartDate(studygroupDto.getStartDate());
        studygroup.setEndDate(studygroupDto.getEndDate());

        BigInteger studyGroup_id= studygroup.getStudyGroup_id();
        String title = studygroup.getTitle();
        String content = studygroup.getContent();
        int learningMaterial_id = studygroup.getLearningMaterial_id();
        String writer = studygroup.getWriter();
        Double x_map = studygroup.getX_map();
        Double y_map = studygroup.getY_map();
        int totalNum = studygroup.getTotalNum();
        int currentNum = studygroup.getCurrentNum();
        LocalDate startDate = studygroup.getStartDate();
        LocalDate endDate = studygroup.getEndDate();

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

        return StudygroupDto.toDto(studygroup);
    }



    public void deleteByStudyGroup_id(int id){
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(id)).orElseThrow(() -> {
            return new IllegalArgumentException("Studygroup Id를 찾을 수 없습니다.");
        });
        studygroupRepository.deleteById(BigInteger.valueOf(id));
    }

    //키워드로 검색
    public List<studygroup> searchWithKeyword(String keyword){
        return studygroupRepository.findByTitleContaining(keyword);
    }

    //키워드, 학습자료로 검색
    public List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword){
        return studygroupRepository.searchWithLearningMaterial_idAndKeyword(learningMaterial_id, keyword);
    }

    //학습자료 조회
    public List<learningmaterial> findAllLearningMaterial(){
        return learningmaterialRepository.findAllLearningMaterial();
    }

}