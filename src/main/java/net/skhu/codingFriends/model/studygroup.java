package net.skhu.codingFriends.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class studygroup {

    @NotEmpty @NotBlank
    @Size(min=10, max=500)
    String content;

    @NotEmpty @NotBlank
    int learningMaterial_id;

    @NotEmpty @NotBlank
    String writer;

    Double x_map;
    Double y_map;

    int totalNum;
    int currentNum;

    private LocalDateTime updateDate;

    @NotEmpty @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @NotEmpty @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
}
