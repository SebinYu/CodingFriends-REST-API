package net.skhu.codingFriends.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.skhu.codingFriends.controller.user.UserController;
import net.skhu.codingFriends.dto.RequestDTO.RegisterRequsetDto;
import net.skhu.codingFriends.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("전체 회원 조회")
    public void findAllUsers() throws Exception {
        // given

        // when, then
        mockMvc.perform(
                        get("/users"))
                .andExpect(status().isOk());


        verify(userService).findAll();
    }

    @Test
    @DisplayName("개별 회원 조회")
    public void findUser() throws Exception {
        //given
        int id = 1;

        //when, then
        mockMvc.perform(
                        get("/users/{id}", id))
                .andExpect(status().isOk());
        verify(userService).findUser(id);
    }

    @Nested
    @DisplayName("회원가입 연결 테스트")
    class registerTest {

        @Test
        @DisplayName("성공 테스트")
        void registerTest_success() throws Exception {
            // given
            RegisterRequsetDto req = new RegisterRequsetDto("username", "passwd1", "passwd1", "name", "email@gmail.com", "userType", "address", "address_detail");

            // when, then
            mockMvc.perform(
                            post("/auth")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.success", is("성공")));

            verify(userService).register(req);
        }


        @Test
        @DisplayName("비밀번호 불일치 테스트")
        void registerTest_passwordMismatch() throws Exception {
            // given
            RegisterRequsetDto req = new RegisterRequsetDto("username", "passwd1", "5", "name", "email@gmail.com", "userType", "address", "address_detail");

            // when, then
            mockMvc.perform(
                            post("/auth")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isBadRequest());

            //verify that the service method is not called
            verify(userService, never()).register(req);
        }


    }
}