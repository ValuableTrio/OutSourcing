package org.example.outsourcing.domain.owner.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerSignupForm {

    private String email;
    private String password;
    private String introduce;

}
