package net.skhu.model;

import lombok.Data;

@Data
public class Pagination {
    int di = 0;        // departmentId


    public String getQueryString() {
        return String.format("di=%d", di);
    }
}
