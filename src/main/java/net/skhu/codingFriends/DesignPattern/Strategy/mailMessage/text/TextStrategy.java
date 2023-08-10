package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text;

import net.skhu.codingFriends.entity.User;

public interface TextStrategy {
    String text(User user, String studygroup_id);
}
