package net.skhu.codingFriends.service.mailMessage.subject;

public class Rejected implements SubjectStrategy {
    @Override
    public String subject() {
        return "스터디 참여거절 안내";
    }
}
