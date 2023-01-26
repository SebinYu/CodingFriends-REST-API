package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text;

import net.skhu.codingFriends.entity.user;

public class RejectedText implements TextStrategy {
    @Override
    public String text(user user, String studyTitle) {
        return "안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 신청하신 ["+ studyTitle +"] 스터디 참여가 아쉽게도 거절되었습니다." + System.lineSeparator() +
                "이외로도 더 많은 스터디가 준비되어 있으니, [" + user.getName() + "]님께서 지속적인 지원해주신다면 분명 좋은 결과가 있을 것이라 생각합니다."+
                System.lineSeparator() + System.lineSeparator() +
                "감사합니다.";
    }
}
