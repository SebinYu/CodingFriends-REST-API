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
@RequestMapping("/user/leader/")
public class LeaderController {
    @Autowired
    ApplyMapper applyMapper;

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    ParticipationMapper participationMapper;

    @Autowired
    UserRepository userRepository;

    //    조직장 페이지: 지원자 관리페이지_첫 화면
    @GetMapping("applicationManage/index")
    public String leader(Model model, Principal principal) {

        String name = principal.getName();
        System.out.println(name);
        List<Map<String, Studygroup>> StudygroupTitleList = participationMapper.findStudygroupTitle(principal.getName());
        System.out.println(StudygroupTitleList.size());
        model.addAttribute("StudygroupTitleList", StudygroupTitleList);

        return "user/leader/applicationManage/index";
    }



    //    조직장 페이지: 지원자 관리페이지_지원 수락
    @GetMapping("applicationManage/detail")
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
    @RequestMapping(value="process", method= RequestMethod.POST, params="cmd=save")
    public String leader(Model model,
                         HttpServletRequest request, Principal principal, Participation participation) {

        String[] checkedStudentID = request.getParameterValues("idChecked");
//        String[] studygroupId = request.getParameterValues("studygroupId");
        String studyGroup_Leader = principal.getName();


        for (int i = 0; i < checkedStudentID.length; i++) {
            int OneStudentId = Integer.parseInt(checkedStudentID[i]);
            Integer[] checkedStudygroupId = participationMapper.findAcceptedUserInfo(OneStudentId);
            int studygroupId = checkedStudygroupId[i];

            participation.setStudentId(OneStudentId);
            participation.setStudygroupId(studygroupId);
            participation.setStudyGroup_Leader(studyGroup_Leader);
            participationMapper.Insert(participation);
        }
        return "user/leader/applicationManage/index";
    }

    @RequestMapping(value="/process", method=RequestMethod.POST, params="cmd=delete")
    public String delete(Model model,HttpServletRequest deleteRequest) {
        String[] idChecked = deleteRequest.getParameterValues("idChecked");
        for (int i = 0; i < idChecked.length; ++i){
            applyMapper.delete(new BigInteger(idChecked[i]));}
        return "user/leader/applicationManage/detail";
    }


}
