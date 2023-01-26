package net.skhu.codingFriends.controller;//package net.skhu.controller;

import net.skhu.codingFriends.config.auth.PrincipalDetails;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static net.skhu.codingFriends.response.Response.success;


@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/mailForLeader/{Studygroup_id}")
    public Response mail(Authentication authentication, @PathVariable("Studygroup_id") Long Studygroup_id ) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        user user = principalDetails.getUser();

        return success(mailService.sendSimpleMessage(user, Studygroup_id),"/mail");
    }
}
