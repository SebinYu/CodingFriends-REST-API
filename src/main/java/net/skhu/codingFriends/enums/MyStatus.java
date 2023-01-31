package net.skhu.codingFriends.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum MyStatus {
    Accepted("등록"),
    Undefined("미정"),

    Checked("참여"),
    NotChecked("불참");

//    @Getter
    private final String value;

    MyStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @JsonCreator
    public static MyStatus from(String value) {
        for (MyStatus status : MyStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
