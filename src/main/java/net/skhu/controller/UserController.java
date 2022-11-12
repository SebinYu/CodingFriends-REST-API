package net.skhu.controller;


import net.skhu.config.MyUserDetails;
import net.skhu.dto.Apply;
import net.skhu.dto.Participation;
import net.skhu.dto.Studygroup;
import net.skhu.entity.User;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.StudygroupMapper;
import net.skhu.model.Form5;
import net.skhu.repository.ParticipationRepository;
import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;


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

    @Autowired
    ParticipationRepository ParticipationRepository;

    @GetMapping("user/index")
    public String index(Model model, Principal principal) {

        String name = principal.getName();
        List<Apply> userApplyList = applyMapper.findUserApplyList(name);
        System.out.println(userApplyList);
        model.addAttribute("userApplyList", userApplyList);
        return "user/index";
    }


    @GetMapping("user/leader")
    public String leader(Model model, Principal principal) {

        String name = principal.getName();
        System.out.println(name);
        List<Studygroup> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        System.out.println(StudygroupTitleList);
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);


        List<Apply> ApplierList = participationMapper.findApplier("Do it! 점프 투 파이썬");
        System.out.println(StudygroupTitleList);
        model.addAttribute("ApplierList", ApplierList);
        return "user/leader";
    }

    @PostMapping("user/leader")
    public String leader(Model model, Participation Participation) {
        participationMapper.Insert(Participation);
        return "user/leader";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "cmd=save")
    public String save(Model model, Form5 form5) {
        for (int i = 0; i < form5.getStudentId().length; ++i) {
            Participation participation = new Participation();
            participation.setStudentId(form5.getStudentId()[i]);
            participation.setStudygroupId(form5.getStudygroupId()[i]);
            participation.setStudyGroup_Leader(form5.getStudyGroup_Leader()[i]);
            if (participation.getStudyGroup_Leader().trim().length() > 0)
                ParticipationRepository.save(participation);
        }
        System.out.println(ParticipationRepository);
        return "user/index";

    }
}
