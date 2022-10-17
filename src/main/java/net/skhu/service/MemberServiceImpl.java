//package net.skhu.service;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpSession;
//import org.springframework.stereotype.Service;
//
//import net.skhu.Persistence.MemberDAO;
//import net.skhu.dto.MemberDTO;
//
//@Service // service bean으로 등록
//public class MemberServiceImpl implements MemberService {
//
//    @Inject
//    MemberDAO memberDao;
//    @Override
//    public String loginCheck(MemberDTO dto, HttpSession session) {
//        String name = memberDao.loginCheck(dto);
//        if (name != null) { // 세션 변수 저장
//            session.setAttribute("userid", dto.getUser_id());
//            session.setAttribute("name", name);
//        }
//        return name;
//    }
//
//    @Override
//    public void logout(HttpSession session) {
//        session.invalidate(); // 세션 초기화
//    }
//}
