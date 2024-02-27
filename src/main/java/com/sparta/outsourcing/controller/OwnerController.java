package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.owner.*;
import com.sparta.outsourcing.service.OwnerService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupOwnerRequestDto dto) {
        return ResponseEntity.ok().body(ownerService.signup(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginOwnerRequestDto dto,
                                   HttpSession session
    ) {
        return ResponseEntity.ok().body(ownerService.login(dto, session));
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<?> updateOwner(
            @PathVariable Long ownerId,
            @RequestBody UpdateOwnerRequestDto dto
    ) {
        return ResponseEntity.ok().body(ownerService.update(ownerId, dto));
    }

    @PutMapping("business-number/{ownerId}")
    public ResponseEntity<?> updateBusinessNumber(
            @PathVariable Long ownerId,
            @RequestBody UpdateOwnerBusinessNumberRequestDto dto
    ) {
        return ResponseEntity.ok().body(ownerService.updateBusinessNumber(ownerId, dto));
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<?> deleteOwner(
            @PathVariable Long ownerId,
            @RequestBody DeleteOwnerRequestDto dto
    ) {
        ownerService.delete(ownerId, dto);
        return ResponseEntity.ok().build();
    }
}
