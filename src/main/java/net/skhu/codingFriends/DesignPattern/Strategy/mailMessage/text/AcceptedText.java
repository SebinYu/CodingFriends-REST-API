package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text;

import net.skhu.codingFriends.entity.User;

public class AcceptedText implements TextStrategy {
    @Override
    public String text(User user, String studyTitle) {
        return "안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "축하드립니다! 지난번 신청하신 ["+ studyTitle +"] 스터디 참여승인이 되었습니다." + System.lineSeparator() +
                "[" + user.getName() + "]님의 프로필 페이지에서 승인내역 확인이 가능하니, 확인 부탁드립니다."+
                System.lineSeparator() + System.lineSeparator() +
                "감사합니다.";
    }
}
