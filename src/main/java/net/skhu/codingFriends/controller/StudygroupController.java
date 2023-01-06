package net.skhu.codingFriends.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ActionResult;
import net.skhu.codingFriends.dto.Response;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.StudygroupRepository;
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

    @ApiOperation(value = "전체 게시글 보기", notes = "전체 게시글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("list")
    public List<studygroup> list(){
        return studygroupService.findAll();
    }

    @ApiOperation(value = "개별 게시글 보기", notes = "개별 게시글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail")
    public Response<?> detailGet(@RequestParam BigInteger studyGroup_id){
        return new Response<>("true", "조회 성공", studygroupService.findOneStudygroupInfo(studyGroup_id));
    }

    //로그인 기능 추가이후-> 스터디 신청 기능 적용
    @ApiOperation(value = "개별 게시글에서 스터디 참여신청", notes = "개별 게시글에서 스터디 참여신청한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("detail")
    public studygroup detailPost(@RequestParam BigInteger studyGroup_id){
        return studygroupService.findOneStudygroupInfo(studyGroup_id);
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


    //게시글 생성
    @ApiOperation(value = "게시글 생성 페이지 조회", notes = "게시글 생성 페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("create")
    public List<learningmaterial> createGet() {
        return studygroupService.findAllLearningMaterial();
    }

    @ApiOperation(value = "게시글 생성", notes = "게시글 생성한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("create")
    public ActionResult createPost(@RequestBody studygroup studygroupInfo) {
            studygroupService.insert(studygroupInfo);
        return new ActionResult(true);
    }


    @ApiOperation(value = "게시글 수정 페이지 조회", notes = "게시글 수정 페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("edit")
    public studygroup updateGet(@RequestParam BigInteger studyGroup_id){
        return studygroupService.findOneStudygroupInfo(studyGroup_id);
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글 수정한다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "edit")
    public ActionResult updatePut(@RequestBody studygroup studygroupInfo) {
        studygroupService.update(studygroupInfo);

        return new ActionResult(true);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete")
    public ActionResult delete(@RequestParam int studyGroup_id) {
        studygroupService.deleteByStudyGroup_id(studyGroup_id);
        return new ActionResult(true);
    }

}