package net.skhu.codingFriends.controller.user;

//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ApplyIdVO;
import net.skhu.codingFriends.VO.ParticipationVO;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.EventTest;
import net.skhu.codingFriends.service.LeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static net.skhu.codingFriends.response.Response.success;

@Tag(name = "스터디 조직장 관리페이지", description = "스터디 조직장 관리페이지 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/leader")
public class LeaderController {

    private final LeaderService leaderService;
    private final EventTest test;

    //조직장의 스터디모임 조회를 위한 컨드롤러 메서드 자주사용 -> 메서드 분리
    private Response myStudygroup(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(leaderService.getStudygroups(user),"/User/leader");
    }

    @Operation(summary = "조직장의 스터디모임 조회", description = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/index")
    public Response getMyStudygroup(Authentication authentication) {
        return myStudygroup(authentication);
    }

    @Operation(summary = "조직장의 스터디모임 지원서 보기", description = "조직장의 스터디모임 지원서 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/detail")
    public Response getApplication(Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(leaderService.getApplications(user),"/User/leader/applicationManage/detail");
    }

    @Operation(summary = "스터디 신청 수락", description = "스터디 신청 수락하기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("applicationManage/accept")
    public Response acceptApplication(@RequestBody ApplyIdVO applyIdVO,
                                      Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return success(leaderService.acceptApplication(applyIdVO, user),"/User/leader/applicationManage/accept");
    }

    @Operation(summary = "스터디 신청 거절", description = "스터디 신청 수락하기")
    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("applicationManage/decline")
    public Response declineApplication(@RequestBody ApplyIdVO applyIdVO) {
        return success(leaderService.declineApplication(applyIdVO),"/User/leader/applicationManage/decline");
    }


    @Operation(summary = "조직장의 스터디모임 조회", description = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("participantManage/index")
    public Response getMyStudygroupForParticipantManage(Authentication authentication) {
        return myStudygroup(authentication);
    }

    @Operation(summary = "주차별 스터디 참여내역 조회", description = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("participantManage/detail/{Studygroup_id}")
    public Response getAttendance(@PathVariable("Studygroup_id") Long Studygroup_id) {
        //스터디정보 ->  participantrate 조회
        return success(leaderService.getAttendance(Studygroup_id),"/User/leader/participantManage/detail/{Studygroup_id}");
    }


    @Operation(summary = "조직장의 스터디모임 조회", description = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("attendance/index")
    public Response getMyStudygroupForAttendanceManage(Authentication authentication) {
        return myStudygroup(authentication);
    }

    @Operation(summary = "참여자 정보 조회", description = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("attendance/detail/{Studygroup_id}")
    public Response getParticipants(@PathVariable("Studygroup_id") Long Studygroup_id ) {
        //User id -> 스터디정보 ->  participantrate 조회
        // participantrate는 입력받음

        return success(leaderService.getParticipants(Studygroup_id),"/User/leader/attendance/detail/{Studygroup_id}");
    }

    @Operation(summary = "주차별 참여율 입력", description = "주차별 참여율 입력한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("attendance/detail")
    public Response postAttendance(@RequestBody ParticipationVO participationVO) {

        return success(leaderService.postAttendance(participationVO),"/User/leader/attendance/detail");
    }

}
