package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text;

import net.skhu.codingFriends.entity.user;

public interface TextStrategy {
    String text(user user, String studygroup_id);
}
