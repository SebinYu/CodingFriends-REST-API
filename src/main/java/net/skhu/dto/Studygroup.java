package net.skhu.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
@Data
public class Studygroup implements Serializable {
    private static final long serialVersionUID = 1L;
    BigInteger studyGroup_id;
    String title;
    String content;
    int learningMaterial_id;
    String writer;
    int totalNum;
    int currentNum;
    private Timestamp regDate;
    Date startDate;
    Date endDate;
}
