package net.skhu.codingFriends.service.mailMessage.To;

import lombok.RequiredArgsConstructor;
import net.skhu.codingFriends.entity.participationrate;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.participation.ParticipationRepository;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class SendToUsers implements SendToStrategy{

    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private StudygroupRepository studygroupRepository;



    @Override
    public String[] to(user user, Long studygroup_id) {
        //studygroupRepository.findById - .NullPointerException 확인
        Optional<studygroup> studygroup = studygroupRepository.findById(BigInteger.valueOf(studygroup_id));
        List<participationrate> participationrates = participationRepository.findByStudygroup(studygroup.get());

        String[] userEmails = new String[participationrates.size()];

        for(int i = 0; i < participationrates.size(); i++){
            userEmails[i] = participationrates.get(i).getUser().getEmail();
        }

        return userEmails;
    }
}
