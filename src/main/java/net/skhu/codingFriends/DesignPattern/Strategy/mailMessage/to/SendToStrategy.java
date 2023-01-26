package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.to;

import net.skhu.codingFriends.entity.user;

public interface SendToStrategy {
    String[] to(user user, Long studygroup_id);
}
