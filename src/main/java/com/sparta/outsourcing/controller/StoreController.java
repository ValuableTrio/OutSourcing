package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.config.SessionConst;
import com.sparta.outsourcing.dto.store.StoreInfoForm;
import com.sparta.outsourcing.service.StoreService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService service;

    @PostMapping
    public ResponseEntity<?> createStore(
            HttpSession session,
            @RequestBody StoreInfoForm dto
    ) {

        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createStore(email, dto));
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<?> updateStore(
            HttpSession session,
            @PathVariable Long storeId,
            @RequestBody StoreInfoForm storeInfoForm
    ) {

        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.updateStore(email, storeId, storeInfoForm));
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<?> deleteStore(
            HttpSession session,
            @PathVariable Long storeId
    ) {

        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.deleteStore(email, storeId));
    }

    @GetMapping
    public ResponseEntity<?> getAllStore() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllStore());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<?> getStore(
            @PathVariable Long storeId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getStore(storeId));
    }


    @PostMapping("/{storeId}/dibs")
    public ResponseEntity<?> dibs(
            HttpSession session,
            @PathVariable Long storeId
    ) {
        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.dibs(email, storeId));
    }
}
