package com.sparta.outsourcing.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordRequestDto {
    @NotBlank
    private String originPassword;
    @NotBlank
    private String newPassword;
}
