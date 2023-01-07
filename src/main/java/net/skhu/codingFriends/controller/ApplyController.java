//package net.skhu.codingFriends.controller;
//
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import net.skhu.codingFriends.dto.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigInteger;
//
//@RestController
//@RequiredArgsConstructor
//public class ApplyController {
//
//    @ApiOperation(value = "스터디 참여신청", notes = "스터디 참여신청하기")
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/apply/{studygroup_id}")
//    public Response<?> apply(@PathVariable("studygroup_id") BigInteger studygroup_id, Authentication authentication) {
//        return new Response<>("true", "스터디 신청 성공", userService.findAll());
//    }
//
//}
