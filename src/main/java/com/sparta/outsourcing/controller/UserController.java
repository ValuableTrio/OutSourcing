package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.user.*;
import com.sparta.outsourcing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupUserRequestDto dto) {
        UserInfoDto userInfoDto = userService.signup(dto);
        return ResponseEntity.ok().body(userInfoDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequestDto dto) {
        return ResponseEntity.ok().body(userService.login(dto));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> update(
            @PathVariable("memberId") Long memberId,
            @RequestBody UpdateUserRequestDto dto
    ) {
        return ResponseEntity.ok().body(userService.update(memberId, dto));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> delete(
            @PathVariable("memberId") Long memberId,
            @RequestBody DeleteUserRequestDto dto
    ){
        userService.delete(memberId, dto);
        return ResponseEntity.ok().build();
    }
}
