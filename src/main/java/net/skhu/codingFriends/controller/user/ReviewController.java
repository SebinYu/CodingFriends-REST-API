package net.skhu.codingFriends.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.review;
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

    @ApiOperation(value = "이전 스터디원 조회", notes = "이전 스터디원 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("index/{Studygroup_id}")
    public Response reviewList(@PathVariable("Studygroup_id") Long Studygroup_id, Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.getEXStudygroupFriend(Studygroup_id, user),"/user/review/index/{Studygroup_id}");
    }

    @ApiOperation(value = "스터디원 후기 등록페이지 조회", notes = "스터디원 후기 등록페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail/{Studygroup_id}/{User_id}")
    public Response reviewInputGet(@PathVariable("Studygroup_id") Long Studygroup_id,
                                   @PathVariable("User_id") Long User_id) {

        return success(reviewService.getReviewInputInfo(Studygroup_id, User_id),"/user/review/detail/{Studygroup_id}/{User_id}");
    }

    @ApiOperation(value = "스터디원 후기 등록", notes = "스터디원 후기 등록한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("index/{Studygroup_id}/{User_id}")
    public Response reviewInputPost(@RequestBody review review,
                                    @PathVariable("Studygroup_id") Long Studygroup_id,
                                   @PathVariable("User_id") Long User_id,
                                   Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.postReview(Studygroup_id, user, User_id ,review),"/user/review/index/{Studygroup_id}/{User_id}");
    }

    @ApiOperation(value = "스터디원 후기 등록페이지 조회", notes = "스터디원 후기 등록페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myReviews")
    public Response myReviews(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.getMyReviews(user),"/user/review/myReviews");
    }

    @ApiOperation(value = "후기 이의제기 등록", notes = "후기 이의제기 등록한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("objection/{review_id}")
    public Response putObjection(@PathVariable("review_id") Long review_id,
                                 Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(reviewService.postObjection(review_id, user),"/user/review/objection/{review_id}");
    }
}
