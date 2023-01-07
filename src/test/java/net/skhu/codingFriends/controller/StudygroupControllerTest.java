package net.skhu.codingFriends.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.skhu.codingFriends.service.StudygroupService;
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

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(studygroupController).build();
    }

    @Test
    @DisplayName("전체 게시글 조회")
    public void findBoardsTest() throws Exception {
        // given

        // when, then
        mockMvc.perform(
                        get("/studygroup/list"))
                .andExpect(status().isOk());

        verify(studygroupService).getStudygroups();
    }
}