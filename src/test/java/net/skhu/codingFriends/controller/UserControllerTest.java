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

//    @Rule
//    public ExpectedException thrown = ExpectedException.none();

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
    @DisplayName("회원가입")
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
        @DisplayName("실패 테스트")
        void registerTest_error() throws Exception {

            RegisterRequsetDto req2 = new RegisterRequsetDto("testID2", "tes4", "test1234", "name", "email@gmail.com", "userType", "address", "address_detail");
//            assertThrows(PasswordVerificationException.class, () -> userService.register(req2));
            mockMvc.perform(
                            post("/auth")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(req2)))
                    .andExpect(status().isBadRequest()); //400에러
        }

    }
}