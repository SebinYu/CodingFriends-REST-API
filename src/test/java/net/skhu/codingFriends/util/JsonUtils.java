package net.skhu.codingFriends.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
//테스트 할 때만 필요한 클래스들은
//src/main/java 아래가 아니고, src/test/java 아래에 만들어야 한다.

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
