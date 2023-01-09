package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.LoginRequestDto;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.LoginFailureException;
import net.skhu.codingFriends.exception.MemberNotFoundException;
import net.skhu.codingFriends.exception.UsernameAlreadyExistsException;
import net.skhu.codingFriends.model.UserRegistration;
import net.skhu.codingFriends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public user register(UserRegistration userRegistration) {
        user user = new user();
        user.setUsername(userRegistration.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistration.getPasswd1()));
        user.setName(userRegistration.getName());
        user.setEmail(userRegistration.getEmail());
        user.setEnabled(true);
        user.setUserType("ROLE_USER");
        user.setAddress(userRegistration.getAddress());
        user.setAddress_detail(userRegistration.getAddress_detail());
        return userRepository.save(user);
    }

    public List<user> findAll() {
        return userRepository.findAll();
    }

    public user findUser(int id) {
        return userRepository.findById(id).orElseThrow(()-> {
            return new MemberNotFoundException();
        });
    }

}
