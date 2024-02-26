package com.sparta.outsourcing.dto.owner;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOwnerBusinessNumberRequestDto {
    @NotBlank
    private String businessNumber;
    @NotBlank
    private String password;
}
