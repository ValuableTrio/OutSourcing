package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.menu.MenuInfoForm;
import com.sparta.outsourcing.entity.Menu;
import com.sparta.outsourcing.entity.Owner;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.MenuRepository;
import com.sparta.outsourcing.repository.OwnerRepository;
import com.sparta.outsourcing.repository.StoreRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {
    //Menu, Store repository 주입
    private final OwnerRepository ownerRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    //Store 의 Menu 전체조회
    public List<MenuInfoForm> getAllMenu() {
        return menuRepository.findAll().stream().map(MenuInfoForm::new).toList();
    }

    //Menu 등록
    public String createMenu(String email, Long storeId, MenuInfoForm dto) {
        Owner owner = findBy(email);
        Store store = findBy(storeId);

        validateOwner(store, owner);

        Menu menu = new Menu(
                store,
                dto.getName(),
                dto.getPrice(),
                dto.getDescription()
        );

        menuRepository.save(menu);

        return "메뉴가 생성되었습니다.";
    }

    //Menu 수정
    @Transactional
    public String updateStore(String email, Long storeId, Long menuId, MenuInfoForm dto) {
        Owner owner = findBy(email);
        Store store = findBy(storeId);
        Menu menu = findByMenuId(menuId);

        validateOwner(store, owner);

        menu.update(
                dto.getName(),
                dto.getPrice(),
                dto.getDescription()
        );

        return "메뉴가 수정되었습니다.";
    }

    //Menu 삭제
    public String deleteMenu(String email, Long storeId, Long menuId) {
        Owner owner = findBy(email);
        Store store = findBy(storeId);
        Menu menu = findByMenuId(menuId);

        validateOwner(store, owner);

        menuRepository.delete(menu);

        return "가게가 삭제되었습니다.";
    }

    /**
     * find store by store id
     * @param storeId
     * @return
     */
    private Store findBy(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new EntityNotFoundException("해당 가게가 존재하지 않습니다.")
        );
    }

    /**
     * find owner by email
     * @param email
     * @return
     */
    private Owner findBy(String email) {
        return ownerRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 오너가 존재하지 않습니다.")
        );
    }

    private Menu findByMenuId(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(
                () -> new EntityNotFoundException("해당 메뉴가 존재하지 않습니다.")
        );
    }

    private void validateOwner(Store store, Owner owner) {
        if (store.isNotOwnerMatch(owner)) {
            throw new IllegalArgumentException("해당 가게 점주가 아닙니다.");
        }
    }

}
