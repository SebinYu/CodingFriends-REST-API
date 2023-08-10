package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage;

import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject.SubjectStrategy;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text.TextStrategy;
import net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to.SendToStrategy;

import net.skhu.codingFriends.entity.User;

public class MailInfo {
    private SendToStrategy sendToStrategy;
    private SubjectStrategy subjectStrategy;
    private TextStrategy textStrategy;


    public MailInfo(SendToStrategy sendToStrategy, SubjectStrategy subjectStrategy, TextStrategy textStrategy){
        this.sendToStrategy = sendToStrategy;
        this.subjectStrategy = subjectStrategy;
        this.textStrategy = textStrategy;


    }

    public String[] to(User user, Long studygroup_id){
        return sendToStrategy.to(user, studygroup_id);
    }

    public String subject(){
        return subjectStrategy.subject();
    }

    public String text(User user, String studyTitle){
        return textStrategy.text( user, studyTitle);
    }

}
