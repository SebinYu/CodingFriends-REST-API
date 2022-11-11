package net.skhu.controller;

import net.skhu.dto.Apply;
import net.skhu.dto.Studygroup;
import net.skhu.mapper.ApplyMapper;
import net.skhu.mapper.LearningMaterialMapper;
import net.skhu.mapper.StudygroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("home")
    public String home(Model model,HttpSession session, HttpServletRequest request)throws Exception {
        List<Studygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        return "studygroup/home";
    }

    @RequestMapping("list")
    public String list(Model model,HttpSession session, HttpServletRequest request)throws Exception {
        List<Studygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        model.addAttribute("studygroups", studygroups);
        return "studygroup/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("studygroup", new Studygroup());
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/edit";
    }

    @PostMapping("create")
    public String create(Model model,Studygroup studygroup) {
        studygroupMapper.insert(studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model,
                       @RequestParam(value ="studyGroup_id") BigInteger studyGroup_id) {
        Studygroup studygroup = studygroupMapper.findOne(studyGroup_id);
        model.addAttribute("studygroup", studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "studygroup/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, Studygroup studygroup) {
        studygroupMapper.update(studygroup);
        model.addAttribute("message", "저장했습니다.");
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());
        return "redirect:list";
    }


    @GetMapping("detail")
    public String detail(Model model,
                         @RequestParam("studyGroup_id") BigInteger studyGroup_id) {
        List<Studygroup> studygroups = studygroupMapper.findAll();
        model.addAttribute("studygroups", studygroups);
        Studygroup studygroup = studygroupMapper.findOne(studyGroup_id);
        model.addAttribute("studygroup", studygroup);
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());


        List<Apply> applys = applyMapper.findAll();
        model.addAttribute("applys", applys);
        List<Apply> applyList = applyMapper.findApplyList(studyGroup_id);
        model.addAttribute("applyList", applyList);


        return "studygroup/detail";
    }



    @PostMapping("detail")
    public String edit(Model model, Apply apply) {
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