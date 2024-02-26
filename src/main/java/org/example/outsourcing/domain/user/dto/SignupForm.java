package org.example.outsourcing.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupForm {

    private String email;
    private String password;
    private String introduce;

}
