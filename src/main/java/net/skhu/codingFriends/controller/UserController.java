package net.skhu.codingFriends.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ActionResult;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.model.UserRegistration;
import net.skhu.codingFriends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
//        model.addAttribute(new UserRegistration());
        return new ActionResult(true);
    }

    @PostMapping("register")
    public String registerPost(
            @Valid UserRegistration userRegistration, BindingResult bindingResult)
    {
        if (userService.hasErrors(userRegistration, bindingResult)) {
            return "home/register";
        }
        userService.save(userRegistration);
        return "home/login";
    }

    @GetMapping("registerSuccess")
    public ActionResult registerSurccess() {
        return new ActionResult(true);
    }

}
