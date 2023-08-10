package net.skhu.codingFriends.controller;//package net.skhu.controller;

//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.User;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.mail.BulkMailService;
import net.skhu.codingFriends.service.mail.MailService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static net.skhu.codingFriends.response.Response.success;

@Tag(name = "단일 or 대용량 이메일 알림전송", description = "단일 or 대용량 이메일 알림전송 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;
    private final BulkMailService bulkMailService;

    //authentication을 통한 사용자 정보 불러오기 자주 사용 -> 메서드 분리
    public User findUserInfo(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();
        return user;
    }

    @Operation(summary = "조직장에게 신청자 알림 메일 전송", description = "조직장에게 신청자 알림 이메일 전송한다.")
    @PostMapping("/toLeader/{Studygroup_id}")
    public Response mailToLeader(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
        return success(mailService.sendmailTo(findUserInfo(authentication), "", Studygroup_id),"/mail/toLeader/"+ Studygroup_id);
    }

    @Operation(summary = "신청자에게 스터디 신청 수락 메일 전송", description = "신청자에게 스터디 신청 수락 메일 전송한다.")
    @PostMapping("/toApplier/accepted/{Studygroup_id}")
    public Response mailToAcceptedApplier(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
        return success(mailService.sendmailTo(findUserInfo(authentication), "accepted", Studygroup_id),"/mail/toApplier/accepted/"+ Studygroup_id);
    }

    @Operation(summary = "신청자에게 스터디 신청 거절 메일 전송", description = "신청자에게 스터디 신청 거절 메일 전송한다.")
    @PostMapping("/toApplier/rejected/{Studygroup_id}")
    public Response mailToRejectedApplier(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
        return success(mailService.sendmailTo(findUserInfo(authentication), "rejected", Studygroup_id),"/mail/toApplier/rejected/"+ Studygroup_id);
    }

    @Operation(summary = "다수 인원에게 이벤트 안내 메일 전송", description = "다수 인원에게 이벤트 안내 메일 전송한다.")
    @GetMapping("/toBulkUsers/noticeEvent")
    public CompletableFuture<String> mailToNoticeEventBulkUsers() throws ExecutionException, InterruptedException {
            return bulkMailService.sendMailToNoticeEventBulkUsers();
    }
}
