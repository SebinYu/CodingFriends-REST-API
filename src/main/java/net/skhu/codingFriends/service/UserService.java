package net.skhu.codingFriends.service;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.dto.RequestDTO.RegisterRequsetDto;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.user.PasswordVerificationException;
import net.skhu.codingFriends.exception.MemberNotFoundException;
import net.skhu.codingFriends.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public user register(RegisterRequsetDto registerRequsetDto){
        user user = new user();
        user.setUsername(registerRequsetDto.getUsername());
        if (!registerRequsetDto.getPasswd1().equals(registerRequsetDto.getPasswd2())) {
            throw new PasswordVerificationException();
        }else{
            user.setPassword(bCryptPasswordEncoder.encode(registerRequsetDto.getPasswd1()));
        }
        user.setName(registerRequsetDto.getName());
        user.setEmail(registerRequsetDto.getEmail());
        user.setEnabled(true);
        user.setUserType("ROLE_USER");
        user.setAddress(registerRequsetDto.getAddress());
        user.setAddress_detail(registerRequsetDto.getAddress_detail());
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setUpdateDate(currentDateTime);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<user> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public user findUser(int id) {
        return userRepository.findById(id).orElseThrow(()-> {
            return new MemberNotFoundException();
        });
    }

}
