package net.skhu.controller;


import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.StudygroupMapper;
import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public String index(Model model, Principal principal, HttpServletRequest request, RequestApply apply) {

        String name = principal.getName();
        List<Map<String, RequestApply>> ApplyTitles = applyMapper.findApplyLists(name,"미정");
        model.addAttribute("ApplyTitles", ApplyTitles);

        List<Map<String, RequestApply>> ParticipantTitles = applyMapper.findApplyLists(name,"등록");
        model.addAttribute("ParticipantTitles", ParticipantTitles);

        LocalDate[] EndDate = applyMapper.findEndDate(name,"등록");
        List<RequestStudygroup> EndDateTitle= applyMapper.findEndDateTitle(name,"등록");
        for (int i = 0; i < EndDate.length; i++) {
            LocalDate date1 = EndDate[i];
            LocalDate date2 = LocalDate.now();
            System.out.println(date2.isAfter(date1));
            if(date2.isAfter(date1)){
                RequestStudygroup EndDateTitleN = EndDateTitle.get(i);
                List<RequestStudygroup> EndDateTitleLists = new ArrayList<>();
                EndDateTitleLists.add(EndDateTitleN);
                model.addAttribute("EndDateTitleLists", EndDateTitleLists);
//                apply.setApplyStatus("종료");
//                apply.setUserId(name);
//                applyMapper.update(apply);
            }
        }




            return "user/index";
    }

    @GetMapping("user/review/index")
    public String review(Model model, Principal principal, HttpServletRequest request, RequestApply apply, @RequestParam("StudygroupTitle") String StudygroupTitle) {
        List<RequestStudygroup> names = applyMapper.findExCompany(StudygroupTitle);
        model.addAttribute("names", names);

        return "user/review/index";
    }
}
