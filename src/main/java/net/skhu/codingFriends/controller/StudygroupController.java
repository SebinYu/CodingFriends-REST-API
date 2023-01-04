package net.skhu.codingFriends.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.ActionResult;
import net.skhu.codingFriends.entity.learningmaterial;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudygroupController {

    private final StudygroupService studygroupService;

    //http://localhost:8081/studygroup/list
    @GetMapping("list")
    public List<studygroup> list(){
        return studygroupService.findAll();
    }

    //http://localhost:8081/studygroup/detail
    @GetMapping("detail")
    public studygroup detail(@RequestParam BigInteger studyGroup_id){
        return studygroupService.findOneStudygroupInfo(studyGroup_id);
    }

    //http://localhost:8081/studygroup/search-With-Keyword?keyword=파이썬
    @GetMapping("search-With-Keyword")
    public List<studygroup> searchWithKeyword(@RequestParam String keyword){
        return studygroupService.searchWithKeyword(keyword);
    }

    //http://localhost:8081/studygroup/search-With-LearningMaterial_idAndKeyword/learningMaterial_id=1&keyword=파이썬
    @GetMapping("search-With-LearningMaterial_idAndKeyword")
    public List<studygroup> searchWithLearningMaterial_idAndKeyword(
            @RequestParam Integer learningMaterial_id,
            @RequestParam String keyword){

        return studygroupService.searchWithLearningMaterial_idAndKeyword(learningMaterial_id, keyword);
    }

    //http://localhost:8081/studygroup/create
    @GetMapping("create")
    public List<learningmaterial> createGet() {
        return studygroupService.findAllLearningMaterial();
    }

    //http://localhost:8081/studygroup/create
    @PostMapping("create")
    public ActionResult createPost(@RequestBody studygroup studygroupInfo) {
        try {
            studygroupService.insert(studygroupInfo);
            return new ActionResult(true);
        } catch (Exception e) {
            return new ActionResult(false, e.getMessage());
        }
    }


}