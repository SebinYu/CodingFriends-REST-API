package net.skhu.controller;


import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseApply;
import net.skhu.dto.response.ResponseParticipation;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.ReviewMapper;
import net.skhu.mapper.StudygroupMapper;
//import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import net.skhu.method.OverlapDelete;
import net.skhu.model.Test;
import net.skhu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



@Controller
public class LeaderController {
    @Autowired
    ApplyMapper applyMapper;

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    ReviewMapper reviewMapper;

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
        model.addAttribute("StudygroupTitle", StudygroupTitle);

        Integer studygroupID = participationMapper.findStudygroupid(StudygroupTitle, name);
        model.addAttribute("studygroupID", studygroupID);

        List<Map<String, ResponseApply>> ApplierList = applyMapper.findAcceptedAppliers(studygroupID, "미정");
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
    @RequestMapping(value = "/process", method = RequestMethod.POST, params = "cmd=save")
    public String applicationAccepted(Model model,
                                      HttpServletRequest request, Principal principal,
                                      RedirectAttributes redirectAttributes,
                                      ResponseParticipation participation, RequestApply apply) {

        String[] checkedStudentID = request.getParameterValues("idChecked");
        String studyGroup_Leader = principal.getName();
        String[] studygroupID = request.getParameterValues("studygroupID");


        for (int i = 0; i < checkedStudentID.length; i++) {
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

        return "user/leader/participantManage/index";
    }

    // 지원자 관리페이지_지원 거절
    @RequestMapping(value = "/process", method = RequestMethod.POST, params = "cmd=delete")
    public String applicationRejected(Model model, HttpServletRequest deleteRequest) {
        String[] idChecked = deleteRequest.getParameterValues("idChecked");
        for (int i = 0; i < idChecked.length; ++i) {
            applyMapper.delete(new BigInteger(idChecked[i]));
        }
        return "user/leader/applicationManage/index";
    }


    // 지원자 관리페이지_첫 화면
    @GetMapping("user/leader/participantManage/index")
    public String Participant(Model model, Principal principal) {

        String name = principal.getName();
        List<Map<String, RequestStudygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/participantManage/index";
    }


    //    스터디 참여자 정보 조회
    @GetMapping("user/leader/participantManage/detail")
    public String ParticipantInfo(Model model, Principal principal, HttpServletRequest httpServletRequest, @RequestParam("StudygroupTitle") String StudygroupTitle) {

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
        List<Map<String, ResponseParticipation>> WeeklyReport = participationMapper.findWeeklyReport(week, studygroupID);
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
    public String attendanceCheckGet(Model model, Principal principal,
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
    @ResponseBody
    @PostMapping(value = "/attendanceProcess")
    public Object attendanceCheckPost(Model model, HttpServletRequest request, Principal principal, ResponseParticipation participation,
                                      @RequestBody Test checkInfo) {

        String studyGroup_Leader = principal.getName();
        Integer WeekInput = Integer.valueOf(checkInfo.xWeekInput);
        String oneStudygroupID = checkInfo.studygroupID.get(0);
        Integer[] weeks = participationMapper.findAllWeek(oneStudygroupID);
        List<BigInteger> participationIDs = new ArrayList<>();

        for (int i = 0; i < checkInfo.studentId.size(); i++) {
            String oneStudentId = checkInfo.studentId.get(i);


            Integer score = 0;

            participation.setStudentId(oneStudentId);
            participation.setStudygroupId(oneStudygroupID);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participation.setWeek(Integer.valueOf(WeekInput));

            for (int j = 0; j < checkInfo.attendanceCheckedArr.size(); j++) {
                if (oneStudentId.equals(checkInfo.attendanceCheckedArr.get(j))) {
                    participation.setWeeklyAttendance("\uD83D\uDFE2");
                    score += 100;
                } else {
                    participation.setWeeklyAttendance("x");
                }
            }

            for (int z = 0; z < checkInfo.homeworkCheckedArr.size(); z++) {
                if (oneStudentId.equals(checkInfo.homeworkCheckedArr.get(z))) {
                    participation.setWeeklyHomework("\uD83D\uDFE2");
                    score += 100;
                    System.out.println("누적 점수:" + score);
                } else {
                    participation.setWeeklyHomework("x");
                }
            }

//            참여도 점수평균 - (출석+과제) / 2
            Double finalScore = Double.valueOf(score / checkInfo.studentId.size());
            participation.setLectureScore(finalScore);

            participationMapper.Insert(participation);
            participationIDs.add(participation.getParticipationRate_id());



//            Integer[] AccumulatedScores = reviewMapper.findAccumulatedScores(oneStudentId);
//            Integer oneAccumulatedScores = 0;
//            for (int z = 0; z < AccumulatedScores.length; z++) {
//                oneAccumulatedScores =+ AccumulatedScores[i];
//            }
//            System.out.println(oneAccumulatedScores);
//                System.out.println(AccumulatedScores);



        }

        Integer overlapError = 0;

        //중복 제거 알고리즘

        Object[] temp_OverlapDeletedWeeks = Arrays.stream(weeks).distinct().toArray();
        // 객체 어레이의 요소를 정수 어레이로 복사
        Integer[] OverlapDeletedWeeks = Arrays.stream(temp_OverlapDeletedWeeks)
                .map(o -> (Integer) o)
                .toArray(Integer[]::new);

        for (int i = 0; i < participationIDs.size(); i++) {
            System.out.println("입력력 id:" + participationIDs.get(i));
        }

        for (int i = 0; i < OverlapDeletedWeeks.length; i++) {
            System.out.println("중복 제거된 주차: " + OverlapDeletedWeeks[i]);

            if (WeekInput.equals(OverlapDeletedWeeks[i])) {
                System.out.println("주차 중복에러");
                overlapError = 1;
                model.addAttribute("overlapError", overlapError);

                for (int j = 0; j < participationIDs.size(); j++) {
                    participationMapper.delete(participationIDs.get(j));
                }

            }

        }
        return checkInfo;
    }

}
