package net.skhu.codingFriends.service;


import net.skhu.codingFriends.dto.ResponseDTO.MailResponseDTO;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import net.skhu.codingFriends.service.mailMessage.MailInfo;
import net.skhu.codingFriends.service.mailMessage.subject.Accepted;
import net.skhu.codingFriends.service.mailMessage.subject.Applied;
import net.skhu.codingFriends.service.mailMessage.to.SendToOneUser;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender emailSender;
    private final StudygroupRepository studygroupRepository;


    @Transactional(readOnly = true)
    public MailResponseDTO sendSimpleMessage(user user, Long studygroup_id) {

        //이메일 보내기 전략방식
        MailInfo mailInfo = new MailInfo(new SendToOneUser(), new Applied());
        SimpleMailMessage fullMessage = sendMail(mailInfo, user, studygroup_id);

        //json 리턴값
        MailResponseDTO mailResponseDTO = setMailResponseDTO(fullMessage);

        return mailResponseDTO;
    }





    public SimpleMailMessage sendMail(MailInfo mailInfo,user user, Long studygroup_id){
        Optional<studygroup> studygroupOne = studygroupRepository.findById(BigInteger.valueOf(studygroup_id));
        String studyTitle = studygroupOne.get().getTitle();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailInfo.to(user, studygroup_id));
        message.setFrom("codingfriend7@gmail.com");
        message.setSubject(mailInfo.subject());
        message.setText("안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 공지하신 ["+ studyTitle +"] 스터디에 [" + user.getName() + "]님이 참여신청하였습니다."+ System.lineSeparator() +
                "조직장 페이지에서 확인 부탁드립니다."+ System.lineSeparator() + System.lineSeparator() +
                "감사합니다.");

        emailSender.send(message);

        return message;

    }

    public MailResponseDTO setMailResponseDTO(SimpleMailMessage message){
        //message 객체 필드값 json형태 출력
        MailResponseDTO mailResponseDTO = new MailResponseDTO();

        mailResponseDTO.setTo(message.getTo());
        mailResponseDTO.setFrom(message.getFrom());
        mailResponseDTO.setSubject(message.getSubject());
        mailResponseDTO.setText(message.getText());

        return mailResponseDTO;
    }
}
