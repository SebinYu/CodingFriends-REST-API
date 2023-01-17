package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RegisterDto;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.user.PasswordVerificationException;
import net.skhu.codingFriends.exception.user.RegisterFailureException;
import net.skhu.codingFriends.exception.MemberNotFoundException;
import net.skhu.codingFriends.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public user register(RegisterDto registerDto){
        user user = new user();
        user.setUsername(registerDto.getUsername());
        if (!registerDto.getPasswd1().equals(registerDto.getPasswd2())) {
            throw new PasswordVerificationException();
        }
        user.setPassword(bCryptPasswordEncoder.encode(registerDto.getPasswd1()));
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setEnabled(true);
        user.setUserType("ROLE_USER");
        user.setAddress(registerDto.getAddress());
        user.setAddress_detail(registerDto.getAddress_detail());
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
