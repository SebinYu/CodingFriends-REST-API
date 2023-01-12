package net.skhu.codingFriends.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.LeaderService;
import net.skhu.codingFriends.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static net.skhu.codingFriends.response.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/review")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "주차별 스터디 참여내역 조회", notes = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("index/{Studygroup_id}")
    public Response reviewList(@PathVariable("Studygroup_id") Long Studygroup_id, Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.getEXColleague(Studygroup_id, user));
    }

    @ApiOperation(value = "주차별 스터디 참여내역 조회", notes = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("index/{Studygroup_id}")
    public Response reviewInputGet(@PathVariable("Studygroup_id") Long Studygroup_id, Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.getEXColleague(Studygroup_id, user));
    }

    @ApiOperation(value = "주차별 스터디 참여내역 조회", notes = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("index/{Studygroup_id}")
    public Response reviewInputPost(@PathVariable("Studygroup_id") Long Studygroup_id, Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.getEXColleague(Studygroup_id, user));
    }
}
