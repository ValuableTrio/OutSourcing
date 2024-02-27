package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.store.StoreInfoForm;
import com.sparta.outsourcing.service.StoreService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService service;

    private String email = "test@naver.com";

    @PostMapping
    public ResponseEntity<?> createStore(
            //TODO : 유정 정보 넣기
            @RequestBody StoreInfoForm dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createStore(email, dto));
    }

    @PutMapping("/{store-id}")
    public ResponseEntity<?> updateStore(
            //TODO : 유정 정보 넣기
            @PathVariable Long storeId,
            @RequestBody StoreInfoForm storeInfoForm
    ) {


        return ResponseEntity.status(HttpStatus.OK)
                .body(service.updateStore(email, storeId, storeInfoForm));
    }

    @DeleteMapping("/{store-id}")
    public ResponseEntity<?> deleteStore(
            //TODO : 유정 정보 넣기
            @PathVariable Long storeId
    ) {


        return ResponseEntity.status(HttpStatus.OK)
                .body(service.deleteStore(email, storeId));
    }

    @GetMapping
    public ResponseEntity<?> getAllStore() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllStore());
    }

    @GetMapping("/{store-id")
    public ResponseEntity<?> getStore(
            @PathVariable Long storeId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getStore(storeId));
    }


    @PostMapping("/{store-id}/dibs")
    public ResponseEntity<?> dibs(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.dibs(email));
    }
}
