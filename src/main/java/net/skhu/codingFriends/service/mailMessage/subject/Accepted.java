package net.skhu.codingFriends.service.mailMessage.subject;

public class Accepted implements SubjectStrategy {
    @Override
    public String subject() {
        return "스터디 참여승인 안내";
    }
}
