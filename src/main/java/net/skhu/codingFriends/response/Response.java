package net.skhu.codingFriends.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL) //  null 값을 가지는 필드는, JSON 응답에 포함되지 않음
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Response {
    private String success;
    private int code;
//    private String url;
    private Result result;

    public static Response success() { // 4
        return new Response("성공", 0, null);
    }

    public static <T> Response success(T data) { // 5
        return new Response("성공", 0, new Success<>(data));
    }

    public static Response failure(int code, String msg) { // 6
        return new Response("실패", code, new Failure(msg));
    }
}