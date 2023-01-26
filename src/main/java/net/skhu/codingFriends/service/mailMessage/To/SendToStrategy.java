package net.skhu.codingFriends.service.mailMessage.To;

import net.skhu.codingFriends.entity.user;

public interface SendToStrategy {
    String[] to(user user, Long studygroup_id);
}
