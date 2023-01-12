package net.skhu.codingFriends.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.dto.ApplyDto;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.LeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static net.skhu.codingFriends.response.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/leader")
public class LeaderController {

    private final LeaderService leaderService;

    //조직장의 스터디모임 조회 기능 자주사용 -> 함수화
        private Response MyStudygroup (Authentication authentication){
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            user user = principalDetails.getUser();
            return success(leaderService.getStudygroups(user));
    }

    @ApiOperation(value = "조직장의 스터디모임 조회", notes = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/index")
    public Response MyStudygroupGet(Authentication authentication) {
        return MyStudygroup(authentication);
    }

    @ApiOperation(value = "조직장의 스터디모임 지원서 보기", notes = "조직장의 스터디모임 지원서 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/detail")
    public Response applicationGet(Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(leaderService.getApplications(user));
    }

//    @ApiOperation(value = "스터디 신청 수락", notes = "스터디 신청 수락하기")
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("applicationManage/accept")
//    public Response applyAccept(@RequestBody ApplyDto applyDto,
//                          @PathVariable("id") Long studyGroup_id,
//                          Authentication authentication) {
//
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        user user = principalDetails.getUser();
//        return success(studygroupService.apply(applyDto, studyGroup_id, user));
//    }

}
