package net.skhu.codingFriends.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ProfileController {

//    @ApiOperation(value = "사용자 조회", notes = "조직장의 스터디모임 조회한다.")
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("profile")
//    public Response MyStudygroupGet1(Authentication authentication) {
//        //사용자 정보
//        //신청한 스터디 정보
//        //만료된 스터디 조회
//        //총 리뷰점수 조회
//        return MyStudygroup(authentication);
//    }


}
