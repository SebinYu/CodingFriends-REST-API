package net.skhu.codingFriends.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.LeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static net.skhu.codingFriends.response.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/leader")
public class LeaderController {

    private final LeaderService leaderService;

    @ApiOperation(value = "조직장의 스터디모임 조회", notes = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/index")
    public Response MyStudygroupGet(Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(leaderService.getStudygroups(user));
    }

    @ApiOperation(value = "조직장의 스터디모임 지원서 보기", notes = "조직장의 스터디모임 지원서 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/detail")
    public Response applicationGet(Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(leaderService.getApplications(user));
    }

}
