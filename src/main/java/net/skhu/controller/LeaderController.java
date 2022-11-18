package net.skhu.controller;


import net.skhu.dto.Apply;
import net.skhu.dto.Participation;
import net.skhu.dto.Studygroup;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.StudygroupMapper;
import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
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

        String StudygroupTitlePara = StudygroupTitle;
        model.addAttribute("StudygroupTitlePara", StudygroupTitlePara);
        return "user/leader/applicationManage/detail";
    }

    // 지원자 관리페이지_지원 수락
    @RequestMapping(value="/process", method= RequestMethod.POST, params="cmd=save")
    public String applicationAccepted(Model model,
                                      HttpServletRequest request, Principal principal, Participation participation) {

        String[] checkedStudentID = request.getParameterValues("idChecked");
        String studyGroup_Leader = principal.getName();


        for (int i = 0; i < checkedStudentID.length; i++) {
            String OneStudentId = checkedStudentID[i];
            String checkedStudygroupId = participationMapper.findAcceptedUserInfo(OneStudentId);
            System.out.println(checkedStudygroupId);


            participation.setStudentId(OneStudentId);
            participation.setStudygroupId(checkedStudygroupId);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participation.setWeek(0);
            participation.setWeeklyAttendance("미정");
            participation.setWeeklyHomework("미정");
            participationMapper.Insert(participation);
        }
        return "user/leader/applicationManage/index";
    }

    // 지원자 관리페이지_지원 거절
    @RequestMapping(value="/process", method=RequestMethod.POST, params="cmd=delete")
    public String applicationRejected(Model model,HttpServletRequest deleteRequest) {
        String[] idChecked = deleteRequest.getParameterValues("idChecked");
        for (int i = 0; i < idChecked.length; ++i){
            applyMapper.delete(new BigInteger(idChecked[i]));}
        return "user/leader/applicationManage/index";
    }


    // 지원자 관리페이지_첫 화면
    @GetMapping("user/leader/participantManage/index")
    public String Participant (Model model, Principal principal) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/participantManage/index";
    }


//    스터디 참여자 정보 조회
    @GetMapping("user/leader/participantManage/detail")
    public String ParticipantInfo (Model model, Principal principal,  @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        String StudygroupTitlePara = StudygroupTitle;
        model.addAttribute("StudygroupTitlePara", StudygroupTitlePara);


        List<Map<String, Participation>> ParticipationList = participationMapper.findParticipant(studygroupID);
        model.addAttribute("ParticipationList", ParticipationList);

        return "user/leader/participantManage/detail";
    }


    @GetMapping("user/leader/attendance/index")
    public String attendanceIndex(Model model, Principal principal) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/attendance/index";
    }

    // 주차별 참여율 상세
    @GetMapping("user/leader/attendance/detail")
    public String attendanceCheck (Model model, Principal principal,  @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        String StudygroupTitlePara = StudygroupTitle;
        model.addAttribute("StudygroupTitlePara", StudygroupTitlePara);




        List<Map<String, Participation>> ParticipationList = participationMapper.findParticipant(studygroupID);
        model.addAttribute("ParticipationList", ParticipationList);

        return "user/leader/attendance/detail";
    }


    // 지원자 관리페이지_지원 수락
    @RequestMapping(value="/attendanceProcess", method= RequestMethod.POST, params="cmd=check")
    public String attendanceCheck( HttpServletRequest request, Principal principal, Participation participation,
                                  @RequestParam("StudygroupTitle") String StudygroupTitle, RedirectAttributes redirectAttributes) {


        String[] studentId = request.getParameterValues("studentId");
        String[] studygroupID = request.getParameterValues("studygroupID");
        Principal userPrincipal = request.getUserPrincipal();
        String studyGroup_Leader = principal.getName();
        String[] attendanceChecked = request.getParameterValues("attendanceChecked");
        String[] homeworkChecked = request.getParameterValues("homeworkChecked");

        for (int i = 0; i < studentId.length; i++) {
            String oneStudentId = studentId[i];
            String oneStudygroupID = studygroupID[i];
            String oneAttendanceChecked = attendanceChecked[i];
            System.out.println(oneAttendanceChecked);
            String oneHomeworkChecked = homeworkChecked[i];
            System.out.println(oneHomeworkChecked);


            participation.setStudentId(oneStudentId);
            participation.setStudygroupId(oneStudygroupID);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participation.setWeek(1);
            if(oneAttendanceChecked == null){
                participation.setWeeklyAttendance("불참");
            }else {
                participation.setWeeklyAttendance(oneAttendanceChecked);
            }
            participation.setWeeklyHomework(oneHomeworkChecked);
            participationMapper.Insert(participation);
        }

        String referer = request.getHeader("Referer");

        return "redirect:"+ referer;
    }
}

