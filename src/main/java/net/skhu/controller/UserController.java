package net.skhu.controller;


import net.skhu.method.OverlapCheck;
import net.skhu.dto.Review;
import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.ReviewMapper;
import net.skhu.mapper.StudygroupMapper;
import net.skhu.repository.UserRepository;
import net.skhu.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
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
    ReviewMapper reviewMapper;

    //  회원 프로필 페이지
    @GetMapping("index")
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

            }
        }
            return "user/index";
    }
    @GetMapping("review/index")
    public String reviewList(Model model,
                             @RequestParam("StudygroupTitle") String StudygroupTitle, Principal principal) {

        String LoginID = principal.getName();
        String KoreanName = applyMapper.findExCompanyName(LoginID);
        String[] exCompanyLoginIDs = applyMapper.findExCompanyID(StudygroupTitle);
        String[] exCompanyNames = applyMapper.findExCompany(StudygroupTitle);

        for (int i = 0; i < exCompanyNames.length; i++) {

            if(LoginID.equals(exCompanyLoginIDs[i])){
                exCompanyNames = Arrays.stream(exCompanyNames)
                        .filter(item -> !item.equals(KoreanName))
                        .toArray(String[]::new);
            }
        }

        model.addAttribute("exCompanyNames", exCompanyNames);
        model.addAttribute("StudygroupTitle", StudygroupTitle);
        return "user/review/index";
    }

    @GetMapping("review/detail")
    public String reviewInput(Model model, Principal principal,
                             @RequestParam("StudygroupTitle") String StudygroupTitle,
                              @RequestParam("chosenName") String chosenName,
                              @RequestParam(value = "overlapError", required=false) String overlapError
                              ) {

        String LoginID = principal.getName();
        String KoreanName = applyMapper.findExCompanyName(LoginID);
        String[] exCompanyLoginIDs = applyMapper.findExCompanyID(StudygroupTitle);
        String[] exCompanyNames = applyMapper.findExCompany(StudygroupTitle);

        for (int i = 0; i < exCompanyNames.length; i++) {

            if(LoginID.equals(exCompanyLoginIDs[i])){
                exCompanyNames = Arrays.stream(exCompanyNames)
                        .filter(item -> !item.equals(KoreanName))
                        .toArray(String[]::new);
            }
        }


        model.addAttribute("overlapError", overlapError);
        model.addAttribute("exCompanyNames", exCompanyNames);
        model.addAttribute("StudygroupTitle", StudygroupTitle);
        model.addAttribute("chosenName", chosenName);

        return "user/review/detail";
    }

    @PostMapping("review/detail")
   public String reviewInput(Model model, Review review, HttpServletRequest request, Principal principal,
                             RedirectAttributes redirectAttributes, BindingResult bindingResult,
                              @RequestParam("StudygroupTitle") String StudygroupTitle,
                              @RequestParam("chosenName") String chosenName) {
        String LoginID = principal.getName();
        String[] rating = request.getParameterValues("rating");
        String[] ratingContent = request.getParameterValues("ratingContent");
        BigInteger chosenStudentID = reviewMapper.findchosenStudentID(chosenName);
        BigInteger studygroupID = reviewMapper.findStudygroupID(StudygroupTitle);


        Integer ratingNum = 0;
        String ratingContentVal = "";
        String studyGroupPartnerVal = "";
        for (int i = 0; i < rating.length; ++i){
            ratingNum = Integer.valueOf(rating[i]);
            ratingContentVal = ratingContent[i];
        }


        review.setStudentId(chosenStudentID);
        review.setStudygroupId(studygroupID);
        review.setStudyGroupPartner(LoginID);
        review.setReviewScore(Double.valueOf(ratingNum));
        review.setReviewContents(ratingContentVal);
        reviewMapper.Insert(review);

        Integer[] UplodedUser = reviewMapper.findUplodedUser(LoginID);
        String dupCheckValue = OverlapCheck.solution(UplodedUser);

        Integer overlapError = 0;
        if(dupCheckValue == "중복"){
            overlapError = 1;
            model.addAttribute("overlapError", overlapError);
            reviewMapper.delete(ratingContentVal);
        }else {
            model.addAttribute("overlapError", overlapError);
        }
        redirectAttributes.addAttribute("overlapError", overlapError);
        redirectAttributes.addAttribute("StudygroupTitle", StudygroupTitle);
        redirectAttributes.addAttribute("chosenName", chosenName);

        return "redirect:/user/review/detail";
    }

    @GetMapping("review/check")
    public String reviewInput(Model model, Review review, HttpServletRequest request, Principal principal){

        return "user/review/check";
    }
}
