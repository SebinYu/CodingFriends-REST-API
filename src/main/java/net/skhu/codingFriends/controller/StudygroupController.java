package net.skhu.codingFriends.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.dto.RequestDTO.ApplyRequsetDto;
import net.skhu.codingFriends.dto.RequestDTO.StudygroupRequsetDto;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.exception.studygroup.SelfOnlyDeletableException;
import net.skhu.codingFriends.exception.studygroup.SelfOnlyModifiableException;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

import static net.skhu.codingFriends.response.Response.success;

@Tag(name = "스터디 공지 게시판", description = "스터디 공지 게시판 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudygroupController {

    private final StudygroupService studygroupService;


    @Operation(summary= "전체 게시글 보기", description = "전체 게시글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("list")
    public Response getStudygrouplist() {
        return success(studygroupService.getStudygrouplist(),"/studygroup/list");
    }


    @Operation(summary = "개별 게시글 보기", description = "개별 게시글 조회한다. 테스트를 원할시 ({id}: 56 )을 입력해주세요.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail/{id}")
    public Response getStudygroup(@PathVariable("id") Long studyGroup_id){
        return success(studygroupService.getStudygroup(BigInteger.valueOf(studyGroup_id)),"/studygroup/detail/{id}");
    }


    @Operation(summary = "게시글 작성", description = "게시글 작성한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public Response createStudygroup(@RequestBody StudygroupRequsetDto studygroupRequsetDto, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(studygroupService.createStudygroup(studygroupRequsetDto, user),"/studygroup/create");
    }


    @Operation(summary = "게시글 수정 페이지 조회", description = "게시글 수정 페이지 조회한다. 테스트를 원할시 ({id}: 56 )을 입력해주세요.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("edit/{id}")
    public Response getStudygroupForUpdate(@PathVariable("id") Long studyGroup_id){
        return success(studygroupService.getStudygroup(BigInteger.valueOf(studyGroup_id)),"/studygroup/edit/{id}");
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정한다. 테스트를 원할시 ({id}: 56 )을 입력해주세요.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "edit/{id}")
    public Response updateStudygroup(@RequestBody StudygroupRequsetDto studygroupRequsetDto,
                                     @PathVariable("id") Long studygroup_id,
                                     Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        if (user.getName().equals(studygroupService.getStudygroup(BigInteger.valueOf(studygroup_id)).getWriter())) {
            // 로그인된 유저의 글이 맞다면
            return success(studygroupService.updateStudygroup(Math.toIntExact(studygroup_id), studygroupRequsetDto),"/studygroup/edit/{id}");
        } else {
            throw new SelfOnlyModifiableException();

        }
    }

    @Operation(summary = "게시글 삭제", description = "게시글 삭제한다. ")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/{id}")
    public Response deleteStudygroupById(@PathVariable("id") Long studygroup_id, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        if (user.getName().equals(studygroupService.getStudygroup(BigInteger.valueOf(studygroup_id)).getWriter())) {
            // 로그인된 유저가 글 작성자와 같다면
            studygroupService.deleteStudygroupById(Math.toIntExact(studygroup_id)); // 이 메소드는 반환값이 없으므로 따로 삭제 수행해주고, 리턴에는 null을 넣어줌
            return success("/studygroup/delete/{id}");
        } else {
            throw new SelfOnlyDeletableException();
        }
    }

    @Operation(summary = "키워드로 게시글 검색", description = "키워드로 게시글 검색한다.  테스트를 원할시 ({keyword}: 파이썬 )을 입력해주세요.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("search-With-Keyword/{keyword}")
    public List<Studygroup> searchStudygroupWithKeyword(@PathVariable("keyword") String keyword){
        return studygroupService.searchStudygroupWithKeyword(keyword);
    }

    @Operation(summary = "키워드/학습자료로 게시글 검색", description = "키워드/학습자료 번호로 게시글 검색한다. 테스트를 원할시 ({keyword}: 파이썬, {learningMaterial_id}: 1 )을 입력해주세요.\"")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("search-With-LearningMaterial_idAndKeyword/{learningMaterial_id}/{keyword}")
    public List<Studygroup> searchStudygroupWithLearningMaterialIdAndKeyword(
            @PathVariable("learningMaterial_id") Long learningMaterial_id,
            @PathVariable("keyword") String keyword){

        return studygroupService.searchStudygroupWithLearningMaterialIdAndKeyword(Math.toIntExact(learningMaterial_id), keyword);
    }

    @Operation(summary = "스터디 참여신청", description = "스터디 참여신청한다. 테스트를 원할시 ({id}: 56 )을 입력해주세요." )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("apply/{studyGroup_id}")
    public Response applyStudygroup(@RequestBody ApplyRequsetDto applyRequsetDto,
                          @PathVariable("studyGroup_id") Long studyGroup_id,
                          Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(studygroupService.applyStudygroup(applyRequsetDto, studyGroup_id, user),"/studygroup/apply/{studyGroup_id}");
    }


}