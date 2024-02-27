package com.sparta.outsourcing.dto.owner;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOwnerRequestDto {
    @NotBlank
    private String password;
}
