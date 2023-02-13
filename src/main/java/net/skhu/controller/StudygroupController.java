package net.skhu.controller;

import net.skhu.dto.MailDTO;
import net.skhu.dto.ViewReviewInfo;
import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseApply;
import net.skhu.dto.response.ResponseParticipation;
import net.skhu.dto.response.ResponseStudygroup;
import net.skhu.mapper.*;
import net.skhu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.swap;


@Controller
@RequestMapping("/studygroup")
public class StudygroupController {

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    LearningMaterialMapper learningMaterialMapper;

    @Autowired
    ApplyMapper applyMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ParticipationMapper participationMapper;

    @Autowired
    private MailService mailService;


    @GetMapping("home")
    public String home(Model model) throws Exception {
        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        return "studygroup/home";
    }

    @GetMapping("list")
    public String list(Model model) throws Exception {
        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        return "studygroup/list";
    }


    @GetMapping("search")
    public String search(Model model, HttpServletRequest httpServletRequest) throws Exception {
        String learningMaterial_id = httpServletRequest.getParameter("learningMaterial_id");
        String keyword = httpServletRequest.getParameter("keyword");
        String emptyResultTest1 = studygroupMapper.findSearchedStudygroupKeyword(keyword).toString();
        String emptyResultTest2 = studygroupMapper.findSearchedStudygroup(keyword, learningMaterial_id).toString();
        model.addAttribute("emptyResultTest", emptyResultTest1);

//       learningMaterial_id를 선택하지 않은 경우
        if (learningMaterial_id == null) {
            if (emptyResultTest1 == "[]") {
                model.addAttribute("noResult", "\"검색결과 없음\"");
            } else {
                List<ResponseStudygroup> studygroups = studygroupMapper.findSearchedStudygroupKeyword(keyword);
                model.addAttribute("studygroups", studygroups);

            }
        } else {
            List<ResponseStudygroup> studygroups = studygroupMapper.findSearchedStudygroup(keyword, learningMaterial_id);
            model.addAttribute("studygroups", studygroups);
        }
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/search";
    }


    @GetMapping("create")
    public String createGet(Model model) {
        model.addAttribute("studygroup", new RequestStudygroup());
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/create";
    }

    @PostMapping("create")
    public String createPost(Model model, ResponseStudygroup studygroup,
                             HttpServletRequest request, Principal principal,
                             ResponseParticipation participation
    ) {

//        String[] xMap = request.getParameterValues("xMap");
//        String[] yMap = request.getParameterValues("yMap");
//        System.out.println(xMap[0]);
//        System.out.println(yMap[0]);
//
//        if(xMap[0] == ""){
//
//        }else {
//            studygroup.setX_map(Double.valueOf(xMap[0]));
//        }
//
//        if(yMap[0] == ""){
//
//        }else {
//            studygroup.setY_map(Double.valueOf(yMap[0]));
//
//        }

        studygroupMapper.insert(studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());

//        조직장 참여 등록
//        String name = principal.getName();
//        String StudentId = applyMapper.findID(name);
//        BigInteger StudygroupId = studygroup.getStudyGroup_id();
//        participation.setStudentId(String.valueOf(StudentId));
//        participation.setStudygroupId(String.valueOf(StudygroupId));
//        participation.setStudyGroup_Leader(name);
//        participation.setWeek(0);
//        participation.setWeeklyAttendance("미정");
//        participation.setWeeklyHomework("미정");
//        participationMapper.Insert(participation);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String editGet(Model model,
                       @RequestParam(value ="studyGroup_id") BigInteger studyGroup_id) {
        ResponseStudygroup studygroup = studygroupMapper.findOne(studyGroup_id);
        model.addAttribute("studygroup", studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/edit";
    }

    @PostMapping("edit")
    public String editPost(Model model, ResponseStudygroup studygroup) {
        studygroupMapper.update(studygroup);
        model.addAttribute("message", "저장했습니다.");
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "redirect:list";
    }


    @GetMapping("detail")
    public String detailGet(Model model, Principal principal,
                         @RequestParam("studyGroup_id") BigInteger studyGroup_id) {

//        String sessionName = principal.getName();
//        String writer = studygroupMapper.findWriter(studyGroup_id);
//        String ID = studygroupMapper.findID(writer);
//
//        Integer error = 0;
//
//        if(sessionName == ID){
//            error = 1;
//            model.addAttribute("error", error);
//        }

        Integer[] Applyer = studygroupMapper.findApplyer(studyGroup_id);
        Integer error = 0;
        if(Applyer.length > 0){
            error = 1;
            model.addAttribute("error", error);
        }


        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("studygroups", studygroups);
        ResponseStudygroup studygroup = studygroupMapper.findOne(studyGroup_id);
        model.addAttribute("studygroup", studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());


        List<ResponseApply> applys = applyMapper.findAll();
        model.addAttribute("applys", applys);
        List<ResponseApply> applyList = applyMapper.findApplyList(studyGroup_id);
        model.addAttribute("applyList", applyList);

        String[] name = applyMapper.findAcceptedApplierNameString(studyGroup_id.intValue(), "등록");
        List<String> nameWithoutDuplicate = Arrays.asList(name)
                .stream()
                .distinct()
                .collect(Collectors.toList());

        String[] userID = applyMapper.findAcceptedApplierName(studyGroup_id.intValue(), "등록");
        List<String> userIDWithoutDuplicate = Arrays.asList(userID)
                .stream()
                .distinct()
                .collect(Collectors.toList());

        List<ViewReviewInfo> ViewReviewInfos = new ArrayList<>();
            for (int i = 0; i < userIDWithoutDuplicate.size(); i++) {
//       회원 참여도/ 후기 평균 점수
                String studentID = String.valueOf(userIDWithoutDuplicate.get(i));
                List<Integer> reivews = reviewMapper.findAccumulatedReviewScore(studentID);
                List<Integer> lectureScores = reviewMapper.findAccumulatedLectureScore(studentID);
                System.out.println("사용자" + userIDWithoutDuplicate.get(i));
                for (int j = 0; j < reivews.size(); j++) {
                    System.out.println("리뷰" + reivews.get(j));
                }
                for (int k = 2; k < lectureScores.size(); k++) {
                    System.out.println("참여도" + lectureScores.get(k));
                }

                System.out.println("----------------");
                Integer reivewsEmptyError = 0;
                Integer lectureScoresEmptyError = 0;

                Integer TotalR = 0;
                Integer AvgR = 0;

                if (reivews.size() == 0) {
                    model.addAttribute("reivewsEmptyError", reivewsEmptyError);
                } else {
                    for (int k = 0; k < reivews.size(); k++) {
                        TotalR = TotalR + reivews.get(k);
                    }
                    AvgR = TotalR / reivews.size();
                    System.out.println(AvgR);
                    model.addAttribute("reivewAvg", AvgR);
                }


                Integer TotalL = 0;
                Integer AvgL = 0;

                if (lectureScores.size() == 0) {
                    model.addAttribute("lectureScoresEmptyError", lectureScoresEmptyError);
                } else {
                    for (int k = 2; k < lectureScores.size(); k++) {
                        TotalL = TotalL + lectureScores.get(k);
                    }
                    AvgL = TotalL / (lectureScores.size() - 2);
                    System.out.println(AvgL);
                    model.addAttribute("lectureScoreAvg", AvgL);
                }
                ViewReviewInfo viewReviewInfo = new ViewReviewInfo();
                viewReviewInfo.setName(nameWithoutDuplicate.get(i));
                viewReviewInfo.setTotalReviewScore(AvgR);
                viewReviewInfo.setTotalLectureScore(AvgL);
                ViewReviewInfos.add(i, viewReviewInfo);
            }
            System.out.println(ViewReviewInfos);

        model.addAttribute("ViewReviewInfos", ViewReviewInfos);

        return "studygroup/detail";
    }



    @PostMapping("detail")
    public String detailPost(Model model, RequestApply apply,HttpServletRequest request, MailDTO data) {
        applyMapper.insert(apply);
        model.addAttribute("applys", applyMapper.findAll());
        String[] name = request.getParameterValues("name");
        String[] email = request.getParameterValues("email");

        data.setName(name[0]);
        data.setEmail(email[0]);
        String res = this.mailService.sendSimpleMessage(data);

        return res;
    }


    @RequestMapping("delete")
    public String delete(Model model, @RequestParam("studyGroup_id") BigInteger studyGroup_id) {
        studygroupMapper.delete(studyGroup_id);
        return "redirect:list";
    }




}