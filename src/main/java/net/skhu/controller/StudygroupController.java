package net.skhu.controller;

import net.skhu.dto.request.RequestApply;
import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseApply;
import net.skhu.dto.response.ResponseStudygroup;
import net.skhu.mapper.ApplyMapper;
import net.skhu.mapper.LearningMaterialMapper;
import net.skhu.mapper.StudygroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;


@Controller
@RequestMapping("/studygroup")
public class StudygroupController {

    @Autowired StudygroupMapper studygroupMapper;

    @Autowired LearningMaterialMapper learningMaterialMapper;

    @Autowired
    ApplyMapper applyMapper;

    @GetMapping("home")
    public String home(Model model,HttpSession session, HttpServletRequest request)throws Exception {
        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        return "studygroup/home";
    }

    @GetMapping("list")
    public String list(Model model)throws Exception {
        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        return "studygroup/list";
    }


    @GetMapping("search")
    public String search(Model model, HttpServletRequest httpServletRequest)throws Exception {
        String learningMaterial_id = httpServletRequest.getParameter("learningMaterial_id");
        String keyword = httpServletRequest.getParameter("keyword");
        String emptyResultTest1 = studygroupMapper.findSearchedStudygroupKeyword(keyword).toString();
        String emptyResultTest2 = studygroupMapper.findSearchedStudygroup(keyword, learningMaterial_id).toString();
        model.addAttribute("emptyResultTest", emptyResultTest1);

//       learningMaterial_id를 선택하지 않은 경우
        if(learningMaterial_id == null){
            if( emptyResultTest1 == "[]"){
                model.addAttribute("noResult", "\"검색결과 없음\"");
            } else {
                List<ResponseStudygroup> studygroups = studygroupMapper.findSearchedStudygroupKeyword(keyword);
                model.addAttribute("studygroups", studygroups);

            }
        }else {
            List<ResponseStudygroup> studygroups = studygroupMapper.findSearchedStudygroup(keyword, learningMaterial_id);
            model.addAttribute("studygroups", studygroups);
        }

//



        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/search";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("studygroup", new RequestStudygroup());
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/edit";
    }

    @PostMapping("create")
    public String create(Model model, ResponseStudygroup studygroup) {
        studygroupMapper.insert(studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model,
                       @RequestParam(value ="studyGroup_id") BigInteger studyGroup_id) {
        ResponseStudygroup studygroup = studygroupMapper.findOne(studyGroup_id);
        model.addAttribute("studygroup", studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, ResponseStudygroup studygroup) {
        studygroupMapper.update(studygroup);
        model.addAttribute("message", "저장했습니다.");
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "redirect:list";
    }


    @GetMapping("detail")
    public String detail(Model model,
                         @RequestParam("studyGroup_id") BigInteger studyGroup_id) {
        List<ResponseStudygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("studygroups", studygroups);
        ResponseStudygroup studygroup = studygroupMapper.findOne(studyGroup_id);
        model.addAttribute("studygroup", studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());


        List<ResponseApply> applys = applyMapper.findAll();
        model.addAttribute("applys", applys);
        List<ResponseApply> applyList = applyMapper.findApplyList(studyGroup_id);
        model.addAttribute("applyList", applyList);


        return "studygroup/detail";
    }



    @PostMapping("detail")
    public String edit(Model model, RequestApply apply) {
        applyMapper.insert(apply);
        model.addAttribute("applys", applyMapper.findAll());

        return "studygroup/appliedMember";
    }


    @RequestMapping("delete")
    public String delete(Model model, @RequestParam("studyGroup_id") BigInteger studyGroup_id) {
        studygroupMapper.delete(studyGroup_id);
        return "redirect:list";
    }




}