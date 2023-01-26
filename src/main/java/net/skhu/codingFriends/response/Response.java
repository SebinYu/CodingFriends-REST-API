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
    private String url;
    private Result result;

    public static Response success(String url) { // 4
        return new Response("성공", 0, url, null);
    }

    public static <T> Response success(T data, String url) { // 5
        return new Response("성공", 0, url, new Success<>(data));
    }

    public static Response failure(int code, String msg ,String url) {
        return new Response("실패", code, url, new Failure(msg));
    }
}