package net.skhu.service;


import net.skhu.dto.MailDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender emailSender;

    public String sendSimpleMessage(MailDTO data) {

        System.out.println(data);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(data.getEmail());
        message.setFrom("codingfriend7@gmail.com");
        message.setSubject("스터디 신청 안내");
        message.setText("안녕하세요 사용자님, 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 공지하신 스터디에 참여허 . 확인 부탁드립니다.");
        emailSender.send(message);

        log.info("성공 메세지 {} : ", message);
        return "user/index";
    }
}
