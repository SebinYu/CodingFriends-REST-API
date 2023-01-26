package net.skhu.codingFriends.service.mailMessage;

import net.skhu.codingFriends.service.mailMessage.To.SendToStrategy;

import net.skhu.codingFriends.entity.user;

public class MailInfo {
    private SendToStrategy sendToStrategy;

    public MailInfo(SendToStrategy sendToStrategy){
        this.sendToStrategy = sendToStrategy;
    }

    public String[] to(user user, Long studygroup_id){
        return sendToStrategy.to(user, studygroup_id);
    }


}
