package net.skhu.codingFriends.controller;//package net.skhu.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.mail.BulkMailService;
import net.skhu.codingFriends.service.mail.MailService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static net.skhu.codingFriends.response.Response.success;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;
    private final BulkMailService bulkMailService;

    public user findUserInfo(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();
        return user;
    }

    @ApiOperation(value = "조직장에게 신청자 알림 메일 전송", notes = "조직장에게 신청자 알림 이메일 전송한다.")
    @PostMapping("/toLeader/{Studygroup_id}")
    public Response mailToLeader(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
        return success(mailService.sendmailTo(findUserInfo(authentication), "", Studygroup_id),"/toLeader/"+ Studygroup_id);
    }

//    @ApiOperation(value = "신청자에게 스터디 신청 수락 메일 전송", notes = "신청자에게 스터디 신청 수락 메일 전송한다.")
//    @PostMapping("/toApplier/accepted/{Studygroup_id}")
//    public Response mailToAcceptedApplier(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
//        return success(mailService.sendmailTo(findUserInfo(authentication), Studygroup_id),"/toApplier/accepted/"+ Studygroup_id);
//    }
//
//    @ApiOperation(value = "신청자에게 스터디 신청 거절 메일 전송", notes = "신청자에게 스터디 신청 거절 메일 전송한다.")
//    @PostMapping("/toApplier/rejected/{Studygroup_id}")
//    public Response mailToRejectedApplier(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
//        return success(mailService.sendmailTo(findUserInfo(authentication), Studygroup_id),"/toApplier/rejected/"+ Studygroup_id);
//    }

    @ApiOperation(value = "다수 인원에게 이벤트 안내 메일 전송", notes = "다수 인원에게 이벤트 안내 메일 전송한다.")
    @GetMapping("/toBulkUsers/noticeEvent")
    public String mailToNoticeEventBulkUsers() {
        try{
            bulkMailService.sendMailToNoticeEventBulkUsers();
            return "이벤트 안내 성공";
        }catch (Exception e){
            return "이벤트 안내 에러 발생";
        }
    }
}
