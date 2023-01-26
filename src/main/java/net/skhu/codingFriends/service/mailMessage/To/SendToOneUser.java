package net.skhu.codingFriends.service.mailMessage.To;

import net.skhu.codingFriends.entity.user;

public class SendToOneUser implements SendToStrategy{
    @Override
    public String[] to(user user, Long studygroup_id) {
        return new String[]{user.getEmail()};
    }
}
