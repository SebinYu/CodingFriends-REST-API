package net.skhu.codingFriends.service.mailMessage.text;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.codingFriends.entity.studygroup;
import net.skhu.codingFriends.entity.user;
import net.skhu.codingFriends.repository.studygroup.StudygroupRepository;

import java.math.BigInteger;
import java.util.Optional;

public class AppliedText implements TextStrategy {


    @Override
    public String text(user user, String studyTitle) {

        return "안녕하세요, 스터디원 연결 서비스 코딩프렌즈입니다. " + System.lineSeparator() +
                "지난번 공지하신 ["+ studyTitle +"] 스터디에 [" + user.getName() + "]님이 참여신청하였습니다."+ System.lineSeparator() +
                "조직장 페이지에서 확인 부탁드립니다."+ System.lineSeparator() + System.lineSeparator() +
                "감사합니다.";
    }
}
