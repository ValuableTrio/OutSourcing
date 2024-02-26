package org.example.outsourcing.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.outsourcing.domain.user.dto.SignupForm;
import org.example.outsourcing.domain.user.service.UserService;
import org.example.outsourcing.global.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signup(
            @RequestBody SignupForm dto
    ){
        userService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseMessage.builder()
                        .httpValue(HttpStatus.CREATED.value())
                        .message("회원가입 성공했습니다.").build()
        );
    }
}
