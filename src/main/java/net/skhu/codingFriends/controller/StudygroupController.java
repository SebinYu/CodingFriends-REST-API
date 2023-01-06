package net.skhu.codingFriends.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ActionResult;
import net.skhu.codingFriends.dto.Response;
import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudygroupController {

    private final StudygroupService studygroupService;
    private final UserRepository userRepository;

    @ApiOperation(value = "전체 게시글 보기", notes = "전체 게시글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("list")
    public Response getlist() {
        return new Response("성공", "전체 게시물 리턴", studygroupService.getStudygroups());
    }


    @ApiOperation(value = "개별 게시글 보기", notes = "개별 게시글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail")
    public Response<?> detailGet(@RequestParam BigInteger studyGroup_id){
        return new Response<>("true", "개별 게시물 리턴", studygroupService.getStudygroup(studyGroup_id));
    }

    //로그인 기능 추가이후-> 스터디 신청 기능 적용
    @ApiOperation(value = "개별 게시글에서 스터디 참여신청", notes = "개별 게시글에서 스터디 참여신청한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("detail")
    public Response<?> detailPost(@RequestParam BigInteger studyGroup_id){
        return new Response<>("true", "개별 게시물 리턴", studygroupService.getStudygroup(studyGroup_id));
    }


    //게시글 생성
    @ApiOperation(value = "게시글 작성 페이지 조회", notes = "게시글 작성 페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("create")
    public List<learningmaterial> createGet() {
        return studygroupService.findAllLearningMaterial();
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글 작성한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("create")
    public Response createPost(@RequestBody StudygroupDto studygroupDto) {
        // 원래 로그인을 하면, User 정보는 세션을 통해서 구하고 주면 되지만,
        // 지금은 핵심 개념을 알기 위해서, JWT 로그인은 생략하고, 임의로 findById 로 유저 정보를 넣어줬습니다.

        user user = userRepository.findById(15).get();
        return new Response("성공", "글 작성 성공", studygroupService.write(studygroupDto, user));
    }


    @ApiOperation(value = "게시글 수정 페이지 조회", notes = "게시글 수정 페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("edit")
    public Response<?> updateGet(@RequestParam BigInteger studyGroup_id){
        return new Response<>("true", "개별 게시물 리턴", studygroupService.getStudygroup(studyGroup_id));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글 수정한다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "edit")
    public Response<?> updatePut(@RequestBody StudygroupDto studygroupDto, @RequestParam Integer id) {
        user user = userRepository.findById(13).get();
        return new Response("성공", "글 수정 성공", studygroupService.update(id, studygroupDto));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete")
    public Response delete(@RequestParam int studyGroup_id) {
        studygroupService.deleteByStudyGroup_id(studyGroup_id);
        return new Response("성공", "글 삭제 성공", null);
    }

    @ApiOperation(value = "키워드로 게시글 검색", notes = "키워드로 게시글 검색한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("search-With-Keyword")
    public List<studygroup> searchWithKeyword(@RequestParam String keyword){
        return studygroupService.searchWithKeyword(keyword);
    }

    @ApiOperation(value = "키워드/학습자료로 게시글 검색", notes = "키워드로 게시글 검색한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("search-With-LearningMaterial_idAndKeyword")
    public List<studygroup> searchWithLearningMaterial_idAndKeyword(
            @RequestParam Integer learningMaterial_id,
            @RequestParam String keyword){

        return studygroupService.searchWithLearningMaterial_idAndKeyword(learningMaterial_id, keyword);
    }
}