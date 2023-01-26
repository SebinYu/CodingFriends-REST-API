package net.skhu.codingFriends.service.mailMessage.text;

import net.skhu.codingFriends.entity.user;

public interface TextStrategy {
    String text(user user, String studygroup_id);
}
