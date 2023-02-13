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
        message.setText("안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 공지하신 스터디에 '" + data.getName() + "'님이 참여신청하였습니다."+ System.lineSeparator() +
                "조직장 페이지에서 확인 부탁드립니다."+ System.lineSeparator() + System.lineSeparator() +
                "감사합니다.");
        emailSender.send(message);

        log.info("성공 메세지 {} : ", message);
        return "redirect:/user/index";
    }
}
