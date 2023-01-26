package net.skhu.codingFriends.service.mailMessage;

import net.skhu.codingFriends.service.mailMessage.subject.SubjectStrategy;
import net.skhu.codingFriends.service.mailMessage.to.SendToStrategy;

import net.skhu.codingFriends.entity.user;

public class MailInfo {
    private SendToStrategy sendToStrategy;
    private SubjectStrategy subjectStrategy;


    public MailInfo(SendToStrategy sendToStrategy, SubjectStrategy subjectStrategy){
        this.sendToStrategy = sendToStrategy;
        this.subjectStrategy = subjectStrategy;

    }

    public String[] to(user user, Long studygroup_id){
        return sendToStrategy.to(user, studygroup_id);
    }

    public String subject(){
        return subjectStrategy.subject();
    }


}
