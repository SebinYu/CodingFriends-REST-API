package net.skhu.codingFriends.service.mailMessage;

import net.skhu.codingFriends.service.mailMessage.subject.SubjectStrategy;
import net.skhu.codingFriends.service.mailMessage.text.TextStrategy;
import net.skhu.codingFriends.service.mailMessage.to.SendToStrategy;

import net.skhu.codingFriends.entity.user;

public class MailInfo {
    private SendToStrategy sendToStrategy;
    private SubjectStrategy subjectStrategy;
    private TextStrategy textStrategy;


    public MailInfo(SendToStrategy sendToStrategy, SubjectStrategy subjectStrategy, TextStrategy textStrategy){
        this.sendToStrategy = sendToStrategy;
        this.subjectStrategy = subjectStrategy;
        this.textStrategy = textStrategy;


    }

    public String[] to(user user, Long studygroup_id){
        return sendToStrategy.to(user, studygroup_id);
    }

    public String subject(){
        return subjectStrategy.subject();
    }

    public String text(user user, String studyTitle){
        return textStrategy.text( user, studyTitle);
    }

}
