package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to;

import net.skhu.codingFriends.entity.User;

public interface SendToStrategy {
    String[] to(User user, Long studygroup_id);
}
