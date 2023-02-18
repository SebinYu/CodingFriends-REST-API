package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.subject;

public class Notice implements SubjectStrategy {
    @Override
    public String subject() {
        return "서울 거주자 이벤트 공지";
    }
}
