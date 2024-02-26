package com.sparta.outsourcing.dto.owner;

import com.sparta.outsourcing.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OwnerInfoDto {
    private String email;
    private String businessNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OwnerInfoDto of(Owner owner) {
        return new OwnerInfoDto(
                owner.getEmail(),
                owner.getBusinessNumber(),
                owner.getCreatedAt(),
                owner.getUpdatedAt()
        );
    }
}
