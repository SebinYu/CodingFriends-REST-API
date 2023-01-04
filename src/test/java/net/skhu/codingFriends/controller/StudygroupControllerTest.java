package net.skhu.codingFriends.controller;

import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.service.StudygroupService;
import net.skhu.codingFriends.util.JsonUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudygroupController.class) // 테스트할 컨트롤러 클래스를 명시해야 함.
class StudygroupControllerTest {

    // 액션 메소드를 호출하기 위한 객체
    @Autowired
    private MockMvc mvc;

    // 테스트용 가짜 StudentService 객체
    @MockBean
    private StudygroupService studygroupService;

    // 테스트에 사용할 데이터
    studygroup studygroupInfo;
    List<studygroup> studygroups;

    public void prepare() {
        this.studygroupInfo = new studygroup();
        this.studygroupInfo.setStudyGroup_id(BigInteger.valueOf(99));
        this.studygroupInfo.setTitle("JUnit insert 테스트");
        this.studygroupInfo.setContent("설명");
        this.studygroupInfo.setLearningMaterial_id(2);
        this.studygroupInfo.setWriter("글쓴이");
        this.studygroupInfo.setX_map(1.0);
        this.studygroupInfo.setY_map(1.0);
        this.studygroupInfo.setTotalNum(2);
        this.studygroupInfo.setCurrentNum(0);
        this.studygroupInfo.setUpdateDate(LocalDateTime.parse("2023-01-04T19:27:35.1623922"));
        this.studygroupInfo.setStartDate(LocalDate.parse("2022-11-09"));
        this.studygroupInfo.setEndDate(LocalDate.parse("2022-11-09"));

        this.studygroups = new ArrayList<studygroup>();
        this.studygroups.add(this.studygroupInfo);
    }


    @Test
    public void test_student_insert_success() throws Exception {
//        // 테스트 하기 위해서 mock 객체를 설정함.
        Mockito.doNothing().when(studygroupService).insert(ArgumentMatchers.any());
//        // studentService.insert(...) 메소드가 호출되면,
//        // 아무것도 하지 않도록 설정함

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.post("/studygroup/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(this.studygroupInfo)));

        // 서버에서 받아온 json 데이터 확인
        result.andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.success", is(true)));

        // StudentSerivce.insert 메소드가 호출되었고,
        // 파라미터 값이 this.student 객체의 값과 같은지 확인 (equals)
        Mockito.verify(studygroupService).insert(this.studygroupInfo);
    }

}