package net.skhu.codingFriends.service;


import net.skhu.codingFriends.dto.ResponseDTO.MailResponseDTO;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender emailSender;
    private final StudygroupRepository studygroupRepository;

    public MailResponseDTO sendSimpleMessage(user user, Long studygroup_id) {
        Optional<studygroup> studygroupOne = studygroupRepository.findById(BigInteger.valueOf(studygroup_id));
        String studyTitle = studygroupOne.get().getTitle();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom("codingfriend7@gmail.com");
        message.setSubject("스터디 신청 안내");
        message.setText("안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 공지하신 ["+ studyTitle +"] 스터디에 [" + user.getName() + "]님이 참여신청하였습니다."+ System.lineSeparator() +
                "조직장 페이지에서 확인 부탁드립니다."+ System.lineSeparator() + System.lineSeparator() +
                "감사합니다.");

        emailSender.send(message);
        MailResponseDTO mailResponseDTO = new MailResponseDTO();
        mailResponseDTO.setTo(String.valueOf(message.getTo()));
        mailResponseDTO.setFrom(message.getFrom());
        mailResponseDTO.setText(message.getText());

        return mailResponseDTO;
    }
}
