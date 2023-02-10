package net.skhu.codingFriends.dto.RequestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequsetDto {

    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해주세요", required = true, example = "sosow0212")
    @NotBlank(message="아이디를 입력하세요")
    @Size(min=3, max=15,message = "사용자 이름이 너무 짧습니다.")
    String username;

    @ApiModelProperty(value = "비밀번호", required = true, example = "123456")
    @NotBlank(message="비밀번호를 입력하세요")
    @Size(min=6, max=12, message="6 자리 이상 12 자리 이하이어야 합니다.")
    String passwd1;

    @NotBlank(message="비밀번호를 한번 더 입력하세요")
    String passwd2;

    @NotBlank(message="이름을 입력하세요")
    @Size(min=2, max=30)
    String name;

    @NotBlank(message="이메일 주소를 입력하세요")
    @Email(message="이메일 주소가 올바르지 않습니다")
    String email;

    @NotBlank(message="도로명 주소를 입력하세요")
    String address;

    @NotBlank(message="상세주소를 입력하세요")
    String address_detail;
}