package net.skhu.codingFriends.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.repository.StudygroupRepository;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudygroupController {

    private final StudygroupService studygroupService;

    public final StudygroupRepository studygroupRepository;
    @GetMapping("list")
    public List<studygroup> list(){
        return studygroupRepository.findAll();
    }
}