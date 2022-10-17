//package net.skhu.controller;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import net.skhu.dto.MemberDTO;
//import net.skhu.service.MemberService;
//
//@Controller // 컨트롤러 빈으로 등록
//@RequestMapping("/member/*")
//public class MemberController {
//
//    @Inject MemberService memberService;
//    // menu.do를 클릭하면 views/member/login.jsp로 이동
//    @RequestMapping("login")
//    public String login() {
//        return "member/login";
//    }
//
//    @RequestMapping("login_check.do")
//    public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session) {
//        String name = memberService.loginCheck(dto, session);
//        ModelAndView mav = new ModelAndView();
//        if (name != null) { // 로그인 성공 시
//            mav.setViewName("studygroup/list"); // 뷰의 이름
//        } else { // 로그인 실패 시
//            mav.setViewName("member/login");
//            mav.addObject("message", "error");
//        }
//        return mav;
//    }
//
//    @RequestMapping("logout.do")
//    public ModelAndView logout(HttpSession session, ModelAndView mav) {
//        memberService.logout(session);
//        mav.setViewName("member/login");
//        mav.addObject("message", "logout");
//        return mav;
//    }
//}
