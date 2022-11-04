package net.skhu.service;

import net.skhu.entity.User;
import net.skhu.model.UserRegistration;
import net.skhu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserService {

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean hasErrors(UserRegistration userRegistration, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return true;
        if (userRegistration.getPasswd1().equals(userRegistration.getPasswd2()) == false) {
            bindingResult.rejectValue("passwd2", null, "비밀번호가 일치하지 않습니다.");
            return true;
        }
        User user = userRepository.findByUserid(userRegistration.getUserid());
        if (user != null) {
            bindingResult.rejectValue("userid", null, "사용자 아이디가 중복됩니다.");
            return true;
        }
        return false;
    }

    public User createEntity(UserRegistration userRegistration) {
        User user = new User();
        user.setUserid(userRegistration.getUserid());
        user.setPassword(passwordEncoder.encode(userRegistration.getPasswd1()));
        user.setName(userRegistration.getName());
        user.setEmail(userRegistration.getEmail());
        user.setEnabled(true);
        return user;
    }

    public void save(UserRegistration userRegistration) {
        User user = createEntity(userRegistration);
        userRepository.save(user);
    }

}
