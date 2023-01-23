package net.skhu.codingFriends.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.skhu.codingFriends.dto.RequestDTO.StudygroupRequsetDto;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.service.StudygroupService;
import net.skhu.codingFriends.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// TDD 테스트 주도 개발
@ExtendWith(MockitoExtension.class)
class StudygroupControllerTest {

    @InjectMocks
    StudygroupController studygroupController;

    @Mock
    StudygroupService studygroupService;

    @Mock
    UserService userService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(studygroupController).build();
    }

    @Test
    @DisplayName("전체 게시글 조회")
    public void 전체_게시글_조회() throws Exception {
        // given

        // when, then
        mockMvc.perform(
                        get("/studygroup/list"))
                .andExpect(status().isOk());

        verify(studygroupService).getStudygroups();
    }

    @Test
    @DisplayName("게시글 1개 조회")
    public void findBoardTest() throws Exception {
        // given
        BigInteger id = BigInteger.valueOf(2);

        // when, then
        mockMvc.perform(
                        get("/studygroup/detail/{id}", id))
                .andExpect(status().isOk());

        verify(studygroupService).getStudygroup(id);
    }

    @Test
    @DisplayName("게시글 작성")
    public void saveBoardTest() throws Exception {
        // given
        int id = 12;
        user user = userService.findUser(id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());//추가

        StudygroupRequsetDto studygroupRequsetDto = new StudygroupRequsetDto("제목",
                "설명",
                1,
                5,
                0,
                LocalDate.of(2019,11,12),
                LocalDate.of(2019,11,19));

        // when
        mockMvc.perform(
                        post("/studygroup/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studygroupRequsetDto)))
                .andExpect(status().isCreated());

        //then
        verify(studygroupService).write(studygroupRequsetDto, user);
    }


    @Test
    @DisplayName("게시글 삭제")
    public void 게시글_삭제() throws Exception {
        // given
        BigInteger id = BigInteger.valueOf(2);

        // when
        mockMvc.perform(
                        delete("delete/{id}", id))
                .andExpect(status().isOk());

        //then
        verify(studygroupService).getStudygroup(id);
    }
}