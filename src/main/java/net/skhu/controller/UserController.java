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


    //    조직장 페이지: 지원자 관리페이지_첫 화면
    @GetMapping("user/leader/applicationManage/index")
    public String leader(Model model, Principal principal) {

        String name = principal.getName();
        System.out.println(name);
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        System.out.println(StudygroupTitleList.size());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/applicationManage/index";
    }



    //    조직장 페이지: 지원자 관리페이지_지원 수락
    @GetMapping("user/leader/applicationManage/detail")
    public String leaderAppliers(Model model, Principal principal, @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        System.out.println(StudygroupTitleList.size());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        List<Apply> ApplierList = participationMapper.findApplier(StudygroupTitle);
        model.addAttribute("ApplierList", ApplierList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        System.out.println(studygroupID);
        model.addAttribute("studygroupID", studygroupID);

        return "user/leader/applicationManage/detail";
    }

    //    조직장 페이지: 지원자 관리페이지_지원 수락
    @PostMapping("user/leader/applicationManage/detail")
    public String leader(Model model,
                         HttpServletRequest request, Participation participation) {

        String[] studentId = request.getParameterValues("studentId");
        String[] studygroupId = request.getParameterValues("studygroupId");
        String[] studyGroup_Leader = request.getParameterValues("studyGroup_Leader");

        for (int i = 0; i < studentId.length; i++) {
            int OneStudentId = Integer.parseInt(studentId[i]);
            int OneStudygroupId = Integer.parseInt(studygroupId[i]);
            String OneStudyGroup_Leader = studyGroup_Leader[i];

            participation.setStudentId(OneStudentId);
            participation.setStudygroupId(OneStudygroupId);
            participation.setStudyGroup_Leader(OneStudyGroup_Leader);
            participationMapper.Insert(participation);
        }
        return "user/leader/applicationManage/index";
    }

//    @RequestMapping(value = "user/leader/applicationManage/detail" ,method=RequestMethod.POST, params="cmd=delete")
//    public String delete(Model model,HttpServletRequest deleteRequest, Participation participation) {
//        String[] idChecked = deleteRequest.getParameterValues("idChecked");
//        System.out.println(idChecked);
//        for (int i = 0; i < idChecked.length; ++i){
//        participationMapper.delete(Integer.parseInt(idChecked[i]));}
//        return "redirect:user/leader/applicationManage/detail";
//    }


}
