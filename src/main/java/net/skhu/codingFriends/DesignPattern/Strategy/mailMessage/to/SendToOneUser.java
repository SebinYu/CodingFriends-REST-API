package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to;

import net.skhu.codingFriends.entity.User;

public class SendToOneUser implements SendToStrategy{
    @Override
    public String[] to(User user, Long studygroup_id) {
        return new String[]{user.getEmail()};
    }
}
