package com.sparta.outsourcing.dto.user;

import com.sparta.outsourcing.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    private String email;
    private String introduce;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserInfoDto of(User user){
        return new UserInfoDto(
                user.getEmail(),
                user.getIntroduce(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
