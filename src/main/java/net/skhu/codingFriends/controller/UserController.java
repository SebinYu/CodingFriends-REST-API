package net.skhu.codingFriends.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ActionResult;
import net.skhu.codingFriends.dto.Response;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.model.UserRegistration;
import net.skhu.codingFriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "전체 회원 보기", notes = "전체 회원을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Response<?> findAll() {
        return new Response<>("true", "조회 성공", userService.findAll());
    }

    @GetMapping("/")
    public ActionResult index(Model model) {
        return new ActionResult(true);
    }

    @GetMapping("userInfo")
    public List<user> userInfo() {
        return userService.findAll();
    }

    @GetMapping("login")
    public ActionResult login() {
        return new ActionResult(true);
    }

    @GetMapping("register")
    public ActionResult registerGet() {
        return new ActionResult(true);
    }

//    @PostMapping("register")
//    public ActionResult registerPost(
//            @RequestBody @Valid UserRegistration userRegistration, BindingResult bindingResult)
//    {
//        if (userService.hasErrors(userRegistration, bindingResult)) {
//            return new ActionResult(false);
//        }
//        userService.save(userRegistration);
//        return new ActionResult(true);
//    }

    @GetMapping("registerSuccess")
    public ActionResult registerSurccess() {
        return new ActionResult(true);
    }

}
