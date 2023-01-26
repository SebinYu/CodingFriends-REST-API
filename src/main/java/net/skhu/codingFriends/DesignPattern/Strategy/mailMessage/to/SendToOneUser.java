package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to;

import net.skhu.codingFriends.entity.user;

public class SendToOneUser implements SendToStrategy{
    @Override
    public String[] to(user user, Long studygroup_id) {
        return new String[]{user.getEmail()};
    }
}
