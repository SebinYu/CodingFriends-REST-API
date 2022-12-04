package net.skhu.controller;


import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseApply;
import net.skhu.dto.response.ResponseParticipation;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.StudygroupMapper;
//import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import net.skhu.model.Test;
import net.skhu.repository.UserRepository;
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
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/applicationManage/index";
    }



    // 지원자 관리페이지_스터디 별 지원자 조회
    @GetMapping("user/leader/applicationManage/detail")
    public String applicationDetail(Model model, Principal principal, @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        List<Map<String, ResponseApply>> ApplierList = applyMapper.findAcceptedAppliers(studygroupID,"미정");
            model.addAttribute("ApplierList", ApplierList);

        String StudygroupTitlePara = StudygroupTitle;
        model.addAttribute("StudygroupTitlePara", StudygroupTitlePara);

        return "user/leader/applicationManage/detail";
    }


//    private Integer getStudygroupID (String StudygroupTitle,String name){
//        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
//        return studygroupID;
//    }

    // 지원자 관리페이지_지원 수락
    @RequestMapping(value="/process", method= RequestMethod.POST, params="cmd=save")
    public String applicationAccepted(Model model,
                                      HttpServletRequest request, Principal principal,
                                      ResponseParticipation participation, RequestApply apply) {

        String[] checkedStudentID = request.getParameterValues("idChecked");
        String studyGroup_Leader = principal.getName();
        String[] studygroupID = request.getParameterValues("studygroupID");


        for (int i = 0; i < checkedStudentID.length ; i++){
            String OneStudentId = checkedStudentID[i];
            String onecheckedStudentID = checkedStudentID[i];
            String StudygroupId = studygroupID[0];

            participation.setStudentId(OneStudentId);
            participation.setStudygroupId(StudygroupId);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participation.setWeek(0);
            participation.setWeeklyAttendance("미정");
            participation.setWeeklyHomework("미정");
            participationMapper.Insert(participation);

            apply.setUserId(onecheckedStudentID);
            apply.setApplyStatus("등록");

            applyMapper.update(apply);
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
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/participantManage/index";
    }


//    스터디 참여자 정보 조회
    @GetMapping("user/leader/participantManage/detail")
    public String ParticipantInfo (Model model, Principal principal, HttpServletRequest httpServletRequest, @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        String StudygroupTitlePara = StudygroupTitle;
        model.addAttribute("StudygroupTitlePara", StudygroupTitlePara);

        List<Map<String, ResponseParticipation>> WeekInfoList = participationMapper.findWeekInfo(studygroupID);
        model.addAttribute("WeekInfoList", WeekInfoList);

        String week = httpServletRequest.getParameter("week");
        List<Map<String, ResponseParticipation>> WeeklyReport = participationMapper.findWeeklyReport(week);
        model.addAttribute("WeeklyReport", WeeklyReport);



        List<Map<String, ResponseParticipation>> ParticipationList = participationMapper.findParticipant(studygroupID);
        model.addAttribute("ParticipationList", ParticipationList);

        return "user/leader/participantManage/detail";
    }


    @GetMapping("user/leader/attendance/index")
    public String attendanceIndexGet(Model model, Principal principal) {

        String name = principal.getName();
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/attendance/index";
    }

    // 주차별 참여율 상세
    @GetMapping("user/leader/attendance/detail")
    public String attendanceCheckGet (Model model, Principal principal,
                                   @RequestParam("StudygroupTitle") String StudygroupTitle) {

        String name = principal.getName();
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        String StudygroupTitlePara = StudygroupTitle;
        model.addAttribute("StudygroupTitlePara", StudygroupTitlePara);


        List<Map<String, ResponseApply>> ParticipationList = applyMapper.findAcceptedAppliers(studygroupID, "등록");
        model.addAttribute("ParticipationList", ParticipationList);

        return "user/leader/attendance/detail";
    }


    // 지원자 관리페이지_주차별 참여이력
//    @ResponseBody
//    @RequestMapping(value="/attendanceProcess", method= RequestMethod.POST)
//    public String attendanceCheckPost(HttpServletRequest request, Principal principal, ResponseParticipation participation,
//                                      @RequestBody Test checkInfo) {
    @PostMapping("user/leader/attendance/detail")
    public String attendanceCheckPost(HttpServletRequest request, Principal principal,
                                      ResponseParticipation participation) {

        String[] studentId = request.getParameterValues("studentId");
        String[] studygroupID = request.getParameterValues("studygroupID");
        String studyGroup_Leader = principal.getName();
        String[] attendanceChecked = request.getParameterValues("attendanceCheckedArr");
        String[] homeworkChecked = request.getParameterValues("homeworkCheckedArr");
            System.out.println("출석id:" + attendanceChecked[0]);


        for (int i = 0; i < studentId.length; i++) {
            String oneStudentId = studentId[i];
            String oneStudygroupID = studygroupID[i];
//            String oneAttendanceCheckedID = attendanceChecked[i];
//            System.out.println("출석id:" + oneAttendanceCheckedID);
//            String oneHomeworkCheckedID = homeworkChecked[i];
//            System.out.println("숙제id:" + oneHomeworkCheckedID);
            Integer score = 0;

            participation.setStudentId(oneStudentId);
            participation.setStudygroupId(oneStudygroupID);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participation.setWeek(1);

//            if(oneStudentId.equals(oneAttendanceCheckedID)){
//                participation.setWeeklyAttendance("\uD83D\uDFE2");
//                score += 10;
//            }else{
//                participation.setWeeklyAttendance("x");
//            }
//
//            if(oneStudentId.equals(oneHomeworkCheckedID)){
//                participation.setWeeklyHomework("\uD83D\uDFE2");
//                score += 10;
//                System.out.println("누적 점수:"+ score);
//            }else{
//                participation.setWeeklyHomework("x");
//            }
            participationMapper.Insert(participation);
        }


        return "redirect:/user/leader/participantManage/index";

    }
}

