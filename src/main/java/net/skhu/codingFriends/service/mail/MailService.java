package net.skhu.codingFriends.service.mail;


import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.MailType;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject.Accepted;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject.Rejected;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text.AcceptedText;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text.AppliedText;
import net.skhu.codingFriends.dto.ResponseDTO.MailResponseDTO;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.exception.studygroup.StudygroupIdNotFound;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.MailInfo;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject.Applied;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text.RejectedText;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to.SendToOneUser;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
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

//    @Transactional(readOnly = true)
//    public MailResponseDTO sendmailToLeader(user user, Long studygroup_id) {
//
//        //이메일 보내기 전략방식
//        MailInfo mailInfo = new MailInfo(new SendToOneUser(), new Applied(), new AppliedText());
//        SimpleMailMessage fullMessage = sendMail(mailInfo, user, studygroup_id);
//
//        //json 리턴값
//        MailResponseDTO mailResponseDTO = setMailResponseDTO(fullMessage);
//
//        return mailResponseDTO;
//    }



    @Transactional(readOnly = true)
    public MailResponseDTO sendmailTo(user user, String mailType, Long studygroup_id) {
        MailType type = MailType.find(mailType);

        //이메일 보내기 전략방식
        MailInfo mailInfo = new MailInfo(type.getSendToStrategy(), type.getSubjectStrategy(), type.getTextStrategy());
        SimpleMailMessage fullMessage = sendMail(mailInfo, user, studygroup_id);

        //json 리턴값
        MailResponseDTO mailResponseDTO = setMailResponseDTO(fullMessage);

        return mailResponseDTO;
    }


    public SimpleMailMessage sendMail(MailInfo mailInfo,user user, Long studygroup_id){
        studygroup studygroup = studygroupRepository.findById(BigInteger.valueOf(studygroup_id)).orElseThrow(() -> {
            return new StudygroupIdNotFound();
        });
        String studyTitle = studygroup.getTitle();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailInfo.to(user, studygroup_id));
        message.setFrom("codingfriend7@gmail.com");
        message.setSubject(mailInfo.subject());
        message.setText(mailInfo.text(user, studyTitle));

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
