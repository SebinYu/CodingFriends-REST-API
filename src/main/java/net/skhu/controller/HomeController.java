package net.skhu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping({"/", "index"})
    public String index() {
        return "home/index";
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
