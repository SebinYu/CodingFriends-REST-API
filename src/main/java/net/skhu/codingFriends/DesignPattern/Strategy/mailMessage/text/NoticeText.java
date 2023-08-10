package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text;

import net.skhu.codingFriends.entity.User;

public class NoticeText implements TextStrategy {
    @Override
    public String text(User user, String studyTitle) {
        return "안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "서울에서 개최되는 오프라인 이벤트가 준비되어있습니다." + System.lineSeparator() +
                "자세한 내역은 홈페이지에서 확인가능하며, 많은 참여해주신다면 감사하겠습니다."+
                System.lineSeparator() + System.lineSeparator() +
                "감사합니다.";
    }
}


