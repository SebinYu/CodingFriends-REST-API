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
public class LeaderController {
    @Autowired
    ApplyMapper applyMapper;

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    ParticipationMapper participationMapper;

    @Autowired
    UserRepository userRepository;


    // 지원자 관리페이지_첫 화면
    @GetMapping("user/leader/applicationManage/index")
    public String applicationManage(Model model, Principal principal) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/applicationManage/index";
    }



    // 지원자 관리페이지_스터디 별 지원자 조회
    @GetMapping("user/leader/applicationManage/detail")
    public String applicationDetail(Model model, Principal principal, @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        List<Apply> ApplierList = participationMapper.findApplier(studygroupID);
        model.addAttribute("ApplierList", ApplierList);
        return "user/leader/applicationManage/detail";
    }

    // 지원자 관리페이지_지원 수락
    @RequestMapping(value="/process", method= RequestMethod.POST, params="cmd=save")
    public String applicationAccepted(Model model,
                                      HttpServletRequest request, Principal principal, Participation participation) {

        String[] checkedStudentID = request.getParameterValues("idChecked");
        String studyGroup_Leader = principal.getName();


        for (int i = 0; i < checkedStudentID.length; i++) {
            int OneStudentId = Integer.parseInt(checkedStudentID[i]);
            Integer checkedStudygroupId = participationMapper.findAcceptedUserInfo(OneStudentId);
            System.out.println(checkedStudygroupId);

            participation.setStudentId(OneStudentId);
            participation.setStudygroupId(checkedStudygroupId);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participationMapper.Insert(participation);
        }
        return "redirect:user/leader/applicationManage/index";
    }

    // 지원자 관리페이지_지원 거절
    @RequestMapping(value="/process", method=RequestMethod.POST, params="cmd=delete")
    public String applicationRejected(Model model,HttpServletRequest deleteRequest) {
        String[] idChecked = deleteRequest.getParameterValues("idChecked");
        for (int i = 0; i < idChecked.length; ++i){
            applyMapper.delete(new BigInteger(idChecked[i]));}
        return "redirect:user/leader/applicationManage/index";
    }


    // 지원자 관리페이지_첫 화면
    @GetMapping("user/leader/participantManage/index")
    public String Participant (Model model, Principal principal) {

        String name = principal.getName();
//        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
//        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/participantManage/index";
    }



}

