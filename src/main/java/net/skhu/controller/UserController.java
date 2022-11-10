package net.skhu.controller;


import net.skhu.config.MyUserDetails;
import net.skhu.dto.Apply;
import net.skhu.entity.User;
import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    ApplyMapper applyMapper;

    @Autowired UserRepository userRepository;

    @RequestMapping("user/index")
    public String index(Model model,Principal principal) {

        String name = principal.getName();
        List<Apply> userApplyList = applyMapper.findUserApplyList(name);
        System.out.println(userApplyList);
        model.addAttribute("userApplyList", userApplyList);
        return "user/index";
    }
}
