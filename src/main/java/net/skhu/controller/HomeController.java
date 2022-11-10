package net.skhu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.model.UserRegistration;
import net.skhu.service.UserService;

@Controller
public class HomeController {

    @Autowired UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }


    @GetMapping("user")
    public Object user(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("login", principal.getAttribute("login"));
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("email", principal.getAttribute("email"));
        model.addAttribute("avatar_url", principal.getAttribute("avatar_url"));
        model.addAttribute("picture", principal.getAttribute("picture"));
        return "user";
    }


    @RequestMapping("login")
    public String login() {
        return "home/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute(new UserRegistration());
        return "home/register";
    }

    @PostMapping("register")
    public String register(Model model,
                           @Valid UserRegistration userRegistration, BindingResult bindingResult)
    {
        if (userService.hasErrors(userRegistration, bindingResult)) {
            return "home/register";
        }
        userService.save(userRegistration);
        return "home/login";
    }

    @RequestMapping("registerSuccess")
    public String registerSurccess() {
        return "home/login";
    }

}
