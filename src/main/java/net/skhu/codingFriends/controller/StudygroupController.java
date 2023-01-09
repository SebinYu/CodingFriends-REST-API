package net.skhu.codingFriends.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.dto.Response;
import net.skhu.codingFriends.dto.StudygroupDto;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.UserRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    @GetMapping("detail/{id}")
    public Response<?> detailGet(@PathVariable("id") Long studyGroup_id){
        return new Response<>("true", "개별 게시물 리턴", studygroupService.getStudygroup(BigInteger.valueOf(studyGroup_id)));
    }

    @ApiOperation(value = "게시글 작성 페이지 조회", notes = "게시글 작성 페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("create/{id}")
    public Long createGet(@PathVariable("id") Long studyGroup_id) {
        return studyGroup_id;
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글 작성한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("create")
    public Response createPost(@RequestBody StudygroupDto studygroupDto, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return new Response("성공", "글 작성 성공", studygroupService.write(studygroupDto, user));
    }


    @ApiOperation(value = "게시글 수정 페이지 조회", notes = "게시글 수정 페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("edit/{id}")
    public Response<?> updateGet(@PathVariable("id") Long studyGroup_id){
        return new Response<>("true", "개별 게시물 리턴", studygroupService.getStudygroup(BigInteger.valueOf(studyGroup_id)));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글 수정한다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "edit/{id}")
    public Response<?> updatePut(@RequestBody StudygroupDto studygroupDto,
                                 @PathVariable("id") Long studygroup_id,
                                 Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        if (user.getName().equals(studygroupService.getStudygroup(BigInteger.valueOf(studygroup_id)).getWriter())) {
            // 로그인된 유저의 글이 맞다면

            return new Response("성공", "글 수정 성공", studygroupService.update(Math.toIntExact(studygroup_id), studygroupDto));
        } else {
            return new Response("실패", "본인 게시물만 수정할 수 있습니다.", null);

        }
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete")
    public Response delete(@PathVariable("id") Long studygroup_id, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        if (user.getName().equals(studygroupService.getStudygroup(BigInteger.valueOf(studygroup_id)).getWriter())) {
            // 로그인된 유저가 글 작성자와 같다면
            studygroupService.deleteByStudyGroup_id(Math.toIntExact(studygroup_id)); // 이 메소드는 반환값이 없으므로 따로 삭제 수행해주고, 리턴에는 null을 넣어줌
            return new Response("성공", "글 삭제 성공", null);
        } else {
            return new Response("실패", "본인 게시물만 삭제할 수 있습니다.", null);
        }
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