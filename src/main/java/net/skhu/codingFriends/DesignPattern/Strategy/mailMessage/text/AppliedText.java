package net.skhu.codingFriends.DesignPattern.Strategy.mailMessage.text;

import net.skhu.codingFriends.entity.user;

public class AppliedText implements TextStrategy {


    @Override
    public String text(user user, String studyTitle) {

        return "안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 공지하신 ["+ studyTitle +"] 스터디에 [" + user.getName() + "]님이 참여신청하였습니다."+ System.lineSeparator() +
                "조직장 페이지에서 확인 부탁드립니다."+ System.lineSeparator() + System.lineSeparator() +
                "감사합니다.";
    }
}
