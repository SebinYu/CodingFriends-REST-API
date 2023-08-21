package net.skhu.codingFriends.controller.user;

//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RequestDTO.RegisterRequsetDto;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.skhu.codingFriends.response.Response.success;

@Tag(name = "로그인/회원가입", description = "로그인/회원가입 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "전체 회원 보기", description = "전체 회원을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Response findAllUsers() {
        return success(userService.findAllUsers(), "/users");
    }

    @Operation(summary="유저 찾기", description = "개별 유저 조회한다. 테스트를 원할시 ({id}: 15 )을 입력해주세요.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public Response findUser(@PathVariable("id") Long id) {
        return success(userService.findUser(Math.toIntExact(id)),"/users/{id}");
    }

    @Operation(summary = "회원가입", description="회원가입 진행한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public Response register(@RequestBody @Valid RegisterRequsetDto registerRequsetDto) {
        return success(userService.register(registerRequsetDto),"/auth");
    }

}
