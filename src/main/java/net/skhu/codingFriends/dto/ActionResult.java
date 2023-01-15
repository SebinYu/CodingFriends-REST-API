package net.skhu.codingFriends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//insert/update/delete 작업을 실행하는 액션 메소드가,
//작업 결과 성공/실패 여부와 에러 메시지를 JSON 포맷 데이터로 출력하기 위해 사용할 객체
public class ActionResult {
    boolean success;
    String message;

    public ActionResult(boolean success) {
        this.success = success;
    }
}
