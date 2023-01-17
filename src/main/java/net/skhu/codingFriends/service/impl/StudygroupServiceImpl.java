package net.skhu.codingFriends.service.impl;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RequestDTO.ApplyRequsetDto;
import net.skhu.codingFriends.dto.RequestDTO.StudygroupRequsetDto;
import net.skhu.codingFriends.dto.ResponseDTO.ApplyResponseDto;
import net.skhu.codingFriends.dto.ResponseDTO.StudygroupResponseDto;
import net.skhu.codingFriends.entity.apply;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.studygroup.StudygroupIdNotFound;
import net.skhu.codingFriends.repository.LearningmaterialRepository;
import net.skhu.codingFriends.repository.apply.ApplyRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
    public List<StudygroupResponseDto> getStudygroups() {
        List<studygroup> studygroups = studygroupRepository.findAll();
        List<StudygroupResponseDto> studygroupRequsetDtos = new ArrayList<>();
        studygroups.forEach(s -> studygroupRequsetDtos.add(StudygroupResponseDto.toDto(s)));
        return studygroupRequsetDtos;
    }

    // 개별 게시물 조회
    @Transactional(readOnly = true)
    public StudygroupResponseDto getStudygroup(BigInteger id) {
        studygroup studygroup = studygroupRepository.findById(id).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        StudygroupResponseDto studygroupResponseDto = StudygroupResponseDto.toDto(studygroup);
        return studygroupResponseDto;
    }


    // 게시물 작성
    @Transactional
    public StudygroupResponseDto write(StudygroupRequsetDto studygroupRequsetDto, user user) {
        studygroup studygroup = new studygroup();
        studygroup.setTitle(studygroupRequsetDto.getTitle());
        studygroup.setContent(studygroupRequsetDto.getContent());
        studygroup.setLearningMaterial_id(studygroupRequsetDto.getLearningMaterial_id());
        studygroup.setWriter(user.getName());
        studygroup.setTotalNum(studygroupRequsetDto.getTotalNum());
        studygroup.setCurrentNum(studygroupRequsetDto.getCurrentNum());
        studygroup.setStartDate(studygroupRequsetDto.getStartDate());
        studygroup.setEndDate(studygroupRequsetDto.getEndDate());

        studygroup.setUser(user);

        studygroupRepository.save(studygroup);
        return StudygroupResponseDto.toDto(studygroup);
    }

    // 게시물 수정
    @Transactional
    public StudygroupResponseDto update(Integer studyGroup_id, StudygroupRequsetDto studygroupRequsetDto) {
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(studyGroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });

        studygroup.setTitle(studygroupRequsetDto.getTitle());
        studygroup.setContent(studygroupRequsetDto.getContent());
        studygroup.setLearningMaterial_id(studygroupRequsetDto.getLearningMaterial_id());
        studygroup.setTotalNum(studygroupRequsetDto.getTotalNum());
        studygroup.setCurrentNum(studygroupRequsetDto.getCurrentNum());
        studygroup.setStartDate(studygroupRequsetDto.getStartDate());
        studygroup.setEndDate(studygroupRequsetDto.getEndDate());

        return StudygroupResponseDto.toDto(studygroup);
    }


    @Transactional
    public void deleteByStudyGroup_id(int id){
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        studygroupRepository.deleteById(BigInteger.valueOf(id));
    }

    //키워드로 검색
    @Transactional
    public List<studygroup> searchWithKeyword(String keyword){
        return studygroupRepository.findByTitleContaining(keyword);
    }

    //키워드, 학습자료로 검색
    @Transactional
    public List<studygroup> searchWithLearningMaterial_idAndKeyword(Integer learningMaterial_id, String keyword){
        return studygroupRepository.searchWithLearningMaterial_idAndKeyword(learningMaterial_id, keyword);
    }

    //학습자료 조회
    @Transactional
    public List<learningmaterial> findAllLearningMaterial(){
        return learningmaterialRepository.findAllLearningMaterial();
    }

    @Transactional
    public ApplyResponseDto apply(ApplyRequsetDto ApplyRequsetDto, Long studyGroup_id, user user) {
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(studyGroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });

        apply apply = new apply();
        apply.setUser(user);
        apply.setStudygroup(studygroup);
        apply.setTitle(studygroup.getTitle());
        apply.setApplyStatus("신청");
        apply.setName(user.getName());
        apply.setApplication(ApplyRequsetDto.getApplication());
        apply.setUpdateDate(LocalDateTime.now());
        applyRepository.save(apply);

        return ApplyResponseDto.toDto(apply);
    }

}