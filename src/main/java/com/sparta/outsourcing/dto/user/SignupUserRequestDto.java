package com.sparta.outsourcing.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignupUserRequestDto {
    @NotBlank(message = "email은 공백일 수 없음")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "해당 이메일 표현식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 공백일 수 없음")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$", message = "비밀번호 형식에 맞지 않습니다.")
    private String password;
}
