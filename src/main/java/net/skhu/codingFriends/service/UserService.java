package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RequestDTO.RegisterRequsetDto;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.user.PasswordVerificationException;
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

    public user register(RegisterRequsetDto registerRequsetDto){
        user user = new user();
        user.setUsername(registerRequsetDto.getUsername());
        if (!registerRequsetDto.getPasswd1().equals(registerRequsetDto.getPasswd2())) {
            throw new PasswordVerificationException();
        }
        user.setPassword(bCryptPasswordEncoder.encode(registerRequsetDto.getPasswd1()));
        user.setName(registerRequsetDto.getName());
        user.setEmail(registerRequsetDto.getEmail());
        user.setEnabled(true);
        user.setUserType("ROLE_USER");
        user.setAddress(registerRequsetDto.getAddress());
        user.setAddress_detail(registerRequsetDto.getAddress_detail());
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
