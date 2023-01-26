package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject;

public class Applied implements SubjectStrategy {
    @Override
    public String subject() {
        return "스터디 신청 안내";
    }
}
