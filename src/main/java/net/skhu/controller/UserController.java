package net.skhu.controller;


import net.skhu.dto.Objection;
import net.skhu.dto.response.ResponseParticipation;
import net.skhu.method.OverlapCheck;
import net.skhu.dto.Review;
import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.mapper.ParticipationMapper;
import net.skhu.mapper.ReviewMapper;
import net.skhu.mapper.StudygroupMapper;
import net.skhu.model.Test;
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

//       회원 참여도/ 후기 평균 점수
        String studentID = applyMapper.findID(name);
        List<Integer> reivews = reviewMapper.findAccumulatedReviewScore(studentID);
        List<Integer> lectureScores = reviewMapper.findAccumulatedLectureScore(studentID);

        for (int i = 0; i < reivews.size(); i++) {
            System.out.println("리뷰" + reivews.get(i));
        }
        for (int i = 0; i < lectureScores.size(); i++) {
            System.out.println("참여도" + lectureScores.get(i));
        }

        System.out.println("----------------");
        System.out.println(reivews.size());
        System.out.println(lectureScores.size());

        Integer reivewsEmptyError = 0;
        Integer lectureScoresEmptyError = 0;

        Integer TotalR = 0;
        Integer AvgR = 0;

        if(reivews.size() == 0){
            model.addAttribute("reivewsEmptyError", reivewsEmptyError);
        }else{
            for (int i = 0; i < reivews.size(); i++) {
                TotalR = TotalR + reivews.get(i);
            }
            AvgR = TotalR/ reivews.size();
            model.addAttribute("reivewAvg", AvgR);
        }


        Integer TotalL = 0;
        Integer AvgL = 0;

        if(lectureScores.size() == 0){
            model.addAttribute("lectureScoresEmptyError", lectureScoresEmptyError);
        }else{
            for (int i = 2; i < lectureScores.size(); i++) {
                TotalL = TotalL + lectureScores.get(i);
            }
            AvgL = TotalL/ (lectureScores.size()-2);
            model.addAttribute("lectureScoreAvg", AvgL);
        }


        System.out.println(AvgR);
        System.out.println(AvgL);


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
    public String reviewCheck(Model model, Principal principal) {

        String name = principal.getName();
        BigInteger studentID = reviewMapper.findStudentId(name);
        BigInteger[] reviewID = reviewMapper.findReviewID(studentID);

        List<List<Review>> AllMyReviewList = new ArrayList<>();

        for (int i = 0; i < reviewID.length; ++i){
            List<Review> MyReviewList =reviewMapper.findAll(reviewID[i]);
            AllMyReviewList.add(MyReviewList);
        }

        model.addAttribute("AllMyReviewList", AllMyReviewList);

        Integer[] objectionNum = reviewMapper.findObjection(studentID);

        Integer totalNum = 0;

        for (int i = 0; i < objectionNum.length; ++i){
            totalNum = totalNum + objectionNum[i];
        }

        if(totalNum >=3 ){
            String outOfChance ="3번 기회 소진: 내년부터 이의신청 가능";
            model.addAttribute("outOfChance", outOfChance);
        }
        return "user/review/check";
    }

    @ResponseBody
    @PostMapping(value = "review/check")
    public Object reviewCheck(Review review,Principal principal, Model model,
                              @RequestBody Test checkInfo) {

        String name = principal.getName();
        BigInteger studentID = reviewMapper.findStudentId(name);
        Integer[] objectionNum = reviewMapper.findObjection(studentID);

        Integer totalNum = 0;

        for (int i = 0; i < objectionNum.length; ++i){
            totalNum = totalNum + objectionNum[i];
        }

        if(totalNum >=3 ){

        }else{
            Integer objectedReviewID = checkInfo.objectedReviewID;

            if( objectedReviewID > 0 ){
                review.setReview_id(BigInteger.valueOf(objectedReviewID));
                review.setObjection(1);
                reviewMapper.update(review);
            }
        }

        return checkInfo;
    }


    @GetMapping("review/checkOutside")
    public String reviewCheckOutside(Model model, @RequestParam("name") String name) {

        BigInteger studentID = reviewMapper.findStudentId(name);
        BigInteger[] reviewID = reviewMapper.findReviewID(studentID);

        List<List<Review>> AllMyReviewList = new ArrayList<>();

        for (int i = 0; i < reviewID.length; ++i){
            List<Review> MyReviewList =reviewMapper.findAll(reviewID[i]);
            AllMyReviewList.add(MyReviewList);
        }

        model.addAttribute("AllMyReviewList", AllMyReviewList);

        Integer[] objectionNum = reviewMapper.findObjection(studentID);

        Integer totalNum = 0;

        for (int i = 0; i < objectionNum.length; ++i){
            totalNum = totalNum + objectionNum[i];
        }

        if(totalNum >=3 ){
            String outOfChance ="3번 기회 소진: 내년부터 이의신청 가능";
            model.addAttribute("outOfChance", outOfChance);
        }
        return "user/review/check";
    }
}
