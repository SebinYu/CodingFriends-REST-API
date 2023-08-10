package net.skhu.codingFriends.controller.user;

//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.dto.RequestDTO.ReviewRequsetDTO;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static net.skhu.codingFriends.response.Response.success;

@Tag(name = "스터디 참여자 성실도 점수관리", description = "스터디 참여자 성실도 점수관리 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "이전 스터디원 조회", description = "이전 스터디원 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("index/{Studygroup_id}")
    public Response reviewList(@PathVariable("Studygroup_id") Long Studygroup_id, Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(reviewService.getEXStudygroupFriend(Studygroup_id, user),"/User/review/index/{Studygroup_id}");
    }

    @Operation(summary = "스터디원 후기 등록페이지 조회", description = "스터디원 후기 등록페이지 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("detail/{Studygroup_id}/{User_id}")
    public Response reviewInputGet(@PathVariable("Studygroup_id") Long Studygroup_id,
                                   @PathVariable("User_id") Long User_id) {

        return success(reviewService.getReviewInputInfo(Studygroup_id, User_id),"/User/review/detail/{Studygroup_id}/{User_id}");
    }

    @Operation(summary = "스터디원 후기 등록", description = "스터디원 후기 등록한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("input")
    public Response reviewInputPost(@RequestBody ReviewRequsetDTO reviewRequsetDTO,
                                   Authentication authentication) {
        //내 이름 제외 스터디 참여자 조회
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(reviewService.postReview(reviewRequsetDTO, user),"/User/review/input");
    }

    @Operation(summary = "내 성실도 점수 및 후기글 조회", description = "내 성실도 점수 및 후기글 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("myReviews")
    public Response myReviews(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(reviewService.getMyReviews(user),"/User/review/myReviews");
    }

    @Operation(summary = "내 성실도 이의제기", description = "내 후기 중 적절하게 평가되지 못한 후기를 이의제기한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("objection/{review_id}")
    public Response putObjection(@PathVariable("review_id") Long review_id,
                                 Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(reviewService.postObjection(review_id, user),"/User/review/objection/{review_id}");
    }
}
