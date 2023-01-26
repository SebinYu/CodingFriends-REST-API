package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject;

public class Accepted implements SubjectStrategy {
    @Override
    public String subject() {
        return "스터디 참여승인 안내";
    }
}
