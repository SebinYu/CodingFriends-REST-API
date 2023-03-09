package net.skhu.codingFriends.controller.user;

//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RequestDTO.RegisterRequsetDto;
import net.skhu.codingFriends.response.Response;
import net.skhu.codingFriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static net.skhu.codingFriends.response.Response.success;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @ApiOperation(value = "전체 회원 보기", notes = "전체 회원을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Response findAllUsers() {
        return success(userService.findAll(), "/users");
    }

//    @ApiOperation(value="유저 찾기", notes = "개별 유저 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public Response findUser(@PathVariable("id") Long id) {
        return success(userService.findUser(Math.toIntExact(id)),"/users/{id}");
    }

//    @ApiOperation(value = "회원가입", notes="회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public Response register(@RequestBody @Valid RegisterRequsetDto registerRequsetDto) {
        return success(userService.register(registerRequsetDto),"/auth");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth/test")
    public void registerTest() {

        for(int i = 0; i < 300; i++){
            RegisterRequsetDto registerRequsetDto = new RegisterRequsetDto();
            registerRequsetDto.setName("세빈");
            registerRequsetDto.setUsername("sebinyu" + i);
            registerRequsetDto.setPasswd1("1234");
            registerRequsetDto.setPasswd2("1234");
            registerRequsetDto.setAddress("서울");
            registerRequsetDto.setAddress_detail("상세주소");
            registerRequsetDto.setEmail(i + "@gmail.com");
            userService.register(registerRequsetDto);
        }
    }

}
