//package net.skhu.codingFriends.controller;
//
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import net.skhu.codingFriends.dto.RegisterDto;
//import net.skhu.codingFriends.response.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.math.BigInteger;
//
//import static net.skhu.codingFriends.response.Response.success;
//
//@RestController
//@RequiredArgsConstructor
//public class ApplyController {
//
//    @ApiOperation(value = "스터디 참여신청", notes = "스터디 참여신청하기")
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/apply")
//    public Response apply(@RequestBody @Valid RegisterDto registerDto) {
//        return success(userService.register(registerDto));
//    }
//
//}
