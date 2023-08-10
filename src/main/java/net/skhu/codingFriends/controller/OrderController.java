package net.skhu.codingFriends.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "스터디 모집일자 순서로 게시글 조회", description = "스터디 모집일자 순서로 게시글 조회 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary= "스터디 모집글 업로드 날짜 순 리스트 조회", description = "스터디 모집글 업로드 날짜 순 리스트를 조회한다.")
    @GetMapping("updateDate")
    public List<Studygroup> updateDate(){
        return orderService.updateDate();
    }

    @Operation(summary= "스터디 시작일 순 리스트 조회", description = "스터디 시작일 순 리스트를 조회한다.")
    @GetMapping("startDate")
    //스터디 시작일 순 리스트 출력
    public List<Studygroup> startDate(){
        return orderService.startDate();
    }

}