package net.skhu.codingFriends.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.VO.ApplyIdVO;
import net.skhu.codingFriends.VO.ParticipationVO;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.LeaderService;
import net.skhu.codingFriends.service.test;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.skhu.codingFriends.response.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/leader")
public class LeaderController {

    private final LeaderService leaderService;
    private final test test;

    //조직장의 스터디모임 조회 기능 자주사용 -> 함수화
        private Response MyStudygroup (Authentication authentication){
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            user user = principalDetails.getUser();
            return success(leaderService.getStudygroups(user),"/user/leader");
    }

    @ApiOperation(value = "조직장의 스터디모임 조회", notes = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/index")
    public Response MyStudygroupGet1(Authentication authentication) {
        return MyStudygroup(authentication);
    }

    @ApiOperation(value = "조직장의 스터디모임 지원서 보기", notes = "조직장의 스터디모임 지원서 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("applicationManage/detail")
    public Response applicationGet1(Authentication authentication) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(leaderService.getApplications(user),"/user/leader/applicationManage/detail");
    }

    @ApiOperation(value = "스터디 신청 수락", notes = "스터디 신청 수락하기")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("applicationManage/accept")
    public Response accept(@RequestBody ApplyIdVO applyIdVO,
                          Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return success(leaderService.accept(applyIdVO, user),"/user/leader/applicationManage/accept");
    }

    @ApiOperation(value = "스터디 신청 거절", notes = "스터디 신청 수락하기")
    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("applicationManage/decline")
    public Response decline(@RequestBody ApplyIdVO applyIdVO) {
        return success(leaderService.decline(applyIdVO),"/user/leader/applicationManage/decline");
    }




    @ApiOperation(value = "조직장의 스터디모임 조회", notes = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("participantManage/index")
    public Response MyStudygroupGet2(Authentication authentication) {
        return MyStudygroup(authentication);
    }

    @ApiOperation(value = "주차별 스터디 참여내역 조회", notes = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("participantManage/detail/{Studygroup_id}")
    public Response participantInfo(@PathVariable("Studygroup_id") Long Studygroup_id) {
        //스터디정보 ->  participantrate 조회
        return success(leaderService.getAttendance(Studygroup_id),"/user/leader/participantManage/detail/{Studygroup_id}");
    }


    @ApiOperation(value = "조직장의 스터디모임 조회", notes = "조직장의 스터디모임 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("attendance/index")
    public Response MyStudygroupGet3(Authentication authentication) {
        return MyStudygroup(authentication);
    }

    @ApiOperation(value = "참여자 정보 조회", notes = "주차별 스터디 참여내역 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("attendance/detail/{Studygroup_id}")
    public Response attendanceCheckGet(@PathVariable("Studygroup_id") Long Studygroup_id ) {
        //user id -> 스터디정보 ->  participantrate 조회
        // participantrate는 입력받음

        return success(leaderService.getParticipants(Studygroup_id),"/user/leader/attendance/detail/{Studygroup_id}");
    }

    @ApiOperation(value = "주차별 참여율 입력", notes = "주차별 참여율 입력한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("attendance/detail")
    public Response attendanceCheckPost(@RequestBody ParticipationVO participationVO) {

        return success(leaderService.postAttendance(participationVO),"/user/leader/attendance/detail");
    }

    // http://localhost:8081/user/leader/attendance/detail
    // 입력 값 예시

//    {
//        "participationDTOList": [
//        {
//            "participationRate_id": 461,
//                "studentId": 12,
//                "studygroupId": 99,
//                "studyGroup_Leader": "세빈",
//                "week": 0,
//                "weeklyAttendance": "참여",
//                "weeklyHomework": "미정",
//                "lectureScore": null,
//                "updateDate": null
//        }
//            ,
//        {
//            "participationRate_id": 461,
//                "studentId": 15,
//                "studygroupId": 99,
//                "studyGroup_Leader": "타이름",
//                "week": 0,
//                "weeklyAttendance": "미정",
//                "weeklyHomework": "미정",
//                "lectureScore": null,
//                "updateDate": null
//        }
//    ]
//    }


}
