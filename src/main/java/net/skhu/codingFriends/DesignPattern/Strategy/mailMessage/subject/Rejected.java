package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject;

public class Rejected implements SubjectStrategy {
    @Override
    public String subject() {
        return "스터디 참여거절 안내";
    }
}
