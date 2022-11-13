package net.skhu.controller;


import net.skhu.dto.Apply;
import net.skhu.dto.Participation;
import net.skhu.dto.Studygroup;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.StudygroupMapper;
import net.skhu.repository.ParticipationRepository;
import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    ApplyMapper applyMapper;

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    ParticipationMapper participationMapper;

    @Autowired
    UserRepository userRepository;


//  회원 프로필 페이지
    @GetMapping("user/index")
    public String index(Model model, Principal principal) {

        String name = principal.getName();
        List<Apply> userApplyList = applyMapper.findUserApplyList(name);
        System.out.println(userApplyList);
        model.addAttribute("userApplyList", userApplyList);
        return "user/index";
    }

}
