package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.config.SessionConst;
import com.sparta.outsourcing.dto.menu.MenuInfoForm;
import com.sparta.outsourcing.service.MenuService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores/{storeId}/menus")
@RequiredArgsConstructor
public class MenuController {
    //Menu Service 주입
    private final MenuService menuService;

    //메뉴 조회
    @GetMapping
    public ResponseEntity<?> getAllMenu() {

        return ResponseEntity.status(HttpStatus.OK).body(menuService.getAllMenu());
    }

    //메뉴 등록
    @PostMapping
    public ResponseEntity<?> createMenu(HttpSession session,
                                        @PathVariable Long storeId,
                                        @RequestBody MenuInfoForm dto) {
        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(email, storeId, dto));
    }

    //메뉴 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<?> updateMenu(HttpSession session,
                                        @PathVariable Long storeId,
                                        @PathVariable Long menuId,
                                        @RequestBody MenuInfoForm dto) {
        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.OK).body(menuService.updateStore(email, storeId, menuId, dto));
    }

    //메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<?> deleteMenu(HttpSession session,
                                        @PathVariable Long storeId,
                                        @PathVariable Long menuId) {
        String email = (String) session.getAttribute(SessionConst.SESSION_USER.name());
        return ResponseEntity.status(HttpStatus.OK).body(menuService.deleteMenu(email, storeId, menuId));
    }
}
