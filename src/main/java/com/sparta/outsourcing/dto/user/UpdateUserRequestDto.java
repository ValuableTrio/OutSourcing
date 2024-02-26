package com.sparta.outsourcing.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
    @NotBlank
    private String introduce;
    @NotBlank
    private String password;
}
