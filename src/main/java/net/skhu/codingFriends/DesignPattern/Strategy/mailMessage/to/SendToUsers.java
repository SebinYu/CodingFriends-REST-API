package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to;

import net.skhu.codingFriends.entity.Participationrate;
import net.skhu.codingFriends.entity.Studygroup;
import net.skhu.codingFriends.entity.User;
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
    public String[] to(User user, Long studygroup_id) {
        //studygroupRepository.findById - .NullPointerException 확인
        Optional<Studygroup> studygroup = studygroupRepository.findById(BigInteger.valueOf(studygroup_id));
        List<Participationrate> participationrates = participationRepository.findByStudygroup(studygroup.get());

        String[] userEmails = new String[participationrates.size()];

        for(int i = 0; i < participationrates.size(); i++){
            userEmails[i] = participationrates.get(i).getUser().getEmail();
        }

        return userEmails;
    }
}
