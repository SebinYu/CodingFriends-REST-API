package net.skhu.codingFriends.service.impl;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ApplyDto;
import net.skhu.codingFriends.dto.RegisterDto;
import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.studygroup.StudygroupIdNotFound;
import net.skhu.codingFriends.repository.LearningmaterialRepository;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudygroupServiceImpl implements StudygroupService {

    public final StudygroupRepository studygroupRepository;

    private final ApplyRepository applyRepository;

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
            return new StudygroupIdNotFound();
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
    public StudygroupDto update(Integer studyGroup_id, StudygroupDto studygroupDto) {
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(studyGroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });

        studygroup.setTitle(studygroupDto.getTitle());
        studygroup.setContent(studygroupDto.getContent());
        studygroup.setLearningMaterial_id(studygroupDto.getLearningMaterial_id());
        studygroup.setX_map(studygroupDto.getX_map());
        studygroup.setY_map(studygroupDto.getY_map());
        studygroup.setTotalNum(studygroupDto.getTotalNum());
        studygroup.setCurrentNum(studygroupDto.getCurrentNum());
        studygroup.setStartDate(studygroupDto.getStartDate());
        studygroup.setEndDate(studygroupDto.getEndDate());

        return StudygroupDto.toDto(studygroup);
    }



    public void deleteByStudyGroup_id(int id){
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
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

    @Transactional
    public ApplyDto apply(ApplyDto ApplyDto, Long studyGroup_id, user user) {
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(studyGroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });

        apply apply = new apply();
        apply.setUser(user);
        apply.setStudygroup(studygroup);
        apply.setTitle(studygroup.getTitle());
        apply.setApplyStatus("신청");
        apply.setName(user.getName());
        apply.setApplication(ApplyDto.getApplication());

        applyRepository.save(apply);

        return ApplyDto.toDto(apply);
    }

}