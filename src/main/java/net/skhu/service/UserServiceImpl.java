package net.skhu.service;

import javax.inject.Inject;

import net.skhu.Persistence.UserDAO;
import net.skhu.dto.UserVO;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Inject
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // 회원 가입 처리
    @Override
    public void register(UserVO userVO) throws Exception {
        userDAO.register(userVO);
    }

//    // 로그인 처리
//    @Override
//    public UserVO login(LoginDTO loginDTO) throws Exception {
//        return userDAO.login(loginDTO);
//    }

}
