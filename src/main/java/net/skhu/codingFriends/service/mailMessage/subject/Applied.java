package net.skhu.codingFriends.service.mailMessage.subject;

public class Applied implements SubjectStrategy {
    @Override
    public String subject() {
        return "스터디 신청 안내";
    }
}
