package org.example.outsourcing.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninForm {

    @Email(message = "이메일 형식이 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수로 입력해야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Size(min = 4, max = 10, message = "비밀번호는 최소 4자 이상, 10자 이하여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "비밀번호는 숫자, 알파벳, 특수문자로 입력해야 합니다.")
    private String password;

}
