package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.store.StoreInfoForm;
import com.sparta.outsourcing.entity.Dibs;
import com.sparta.outsourcing.entity.Owner;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.repository.DibsRepository;
import com.sparta.outsourcing.repository.OwnerRepository;
import com.sparta.outsourcing.repository.StoreRepository;
import com.sparta.outsourcing.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;
    private final DibsRepository dibsRepository;

    public String createStore(String email, StoreInfoForm dto) {
        Owner owner = findBy(email);

        Store store = new Store(
                owner,
                dto.getName(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getDescription(),
                dto.getMinPrice()
        );

        storeRepository.save(store);

        return "가게가 생성되었습니다.";
    }

    public String deleteStore(String email, Long storeId) {
        Owner owner = findBy(email);
        Store store = findBy(storeId);

        validateOwner(store, owner);

        storeRepository.delete(store);

        return "가게가 삭제되었습니다.";
    }


    public String updateStore(String email, Long storeId, StoreInfoForm dto) {
        Owner owner = findBy(email);
        Store store = findBy(storeId);

        validateOwner(store, owner);

        store.update(
                dto.getName(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getDescription(),
                dto.getMinPrice()
        );

        return "가게가 수정되었습니다.";
    }

    @Transactional(readOnly = true)
    public List<StoreInfoForm> getOwnerAllStore(String email) {
        Owner owner = findBy(email);

        return storeRepository.findAllByOwner(owner)
                .stream()
                .map(StoreInfoForm::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public StoreInfoForm getOwnerStore(String email, Long storeId) {
        Owner owner = findBy(email);
        Store store = findBy(storeId);

        validateOwner(store, owner);

        return new StoreInfoForm(store);
    }
    @Transactional(readOnly = true)
    public List<StoreInfoForm> getAllStore(){
        return storeRepository.findAll()
                .stream()
                .map(StoreInfoForm::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public StoreInfoForm getStore(Long storeId) {
        Store store = findBy(storeId);

        return new StoreInfoForm(store);
    }

    public String dibs(String email, Long storeId) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        Store store = findBy(storeId);

        Dibs dibs = new Dibs(store, user);

        if(dibsRepository.existsByUserAndStore(user, store)){
            dibsRepository.deleteByUserAndStore(user, store);
            return "찜이 해제되었습니다";
        }

        dibsRepository.save(dibs);

        return "찜에 등록되었습니다.";
    }

    private void validateOwner(Store store, Owner owner) {
        if (store.isNotOwnerMatch(owner)) {
            throw new IllegalArgumentException("해당 가게 점주가 아닙니다.");
        }
    }

    private Owner findBy(String email) {
        return ownerRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 오너가 존재하지 않습니다.")
        );
    }

    private Store findBy(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new EntityNotFoundException("해당 가게가 존재하지 않습니다.")
        );
    }


}
