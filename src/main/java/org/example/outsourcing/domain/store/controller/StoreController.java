package org.example.outsourcing.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.example.outsourcing.domain.owner.entity.Owner;
import org.example.outsourcing.domain.store.dto.StoreInfoForm;
import org.example.outsourcing.domain.store.service.StoreService;
import org.example.outsourcing.global.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService service;


    @PostMapping
    public ResponseEntity<ResponseMessage> createStore(
            //TODO : 유정 정보 넣기
            @RequestBody StoreInfoForm dto
    ) {
        String email = "test@naver.com";
        service.createStore(email, dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseMessage.builder()
                        .httpValue(HttpStatus.CREATED.value())
                        .message("가게 생성이 완료되었습니다.").build());
    }



}
