package net.skhu.codingFriends.controller;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.service.OrderService;
import net.skhu.codingFriends.service.StudygroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class OrderController {

    private final OrderService orderService;

    //http://localhost:8081/studygroup/updateDate
    //스터디 모집글 업로드 날짜 순 리스트 출력
    @GetMapping("updateDate")
    public List<studygroup> updateDate(){
        return orderService.updateDate();
    }

    //http://localhost:8081/studygroup/startDate
    @GetMapping("startDate")
    //스터디 시작일 순 리스트 출력
    public List<studygroup> startDate(){
        return orderService.startDate();
    }

}