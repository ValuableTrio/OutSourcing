package org.example.outsourcing.domain.store.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.outsourcing.domain.owner.entity.Owner;
import org.example.outsourcing.domain.owner.repository.OwnerRepository;
import org.example.outsourcing.domain.store.dto.StoreInfoForm;
import org.example.outsourcing.domain.store.entity.Store;
import org.example.outsourcing.domain.store.repository.StoreRepository;
import org.example.outsourcing.global.exception.custom.EntityNotMatchedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;

    public Store createStore(String email, StoreInfoForm dto) {
        Owner owner = findBy(email);

        Store store = new Store(
                owner,
                dto.getName(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getDescription(),
                dto.getMinPrice()
        );

        return storeRepository.save(store);
    }



    @Transactional
    public void updateStore(String email, Long storeId, StoreInfoForm dto) {
        Owner owner = findBy(email);

        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new EntityNotFoundException("해당 가게가 존재하지 않습니다.")
        );

        validateOwner(store, owner);

        store.update(
                dto.getName(),
                dto.getAddress(),
                dto.getPhoneNumber(),
                dto.getDescription(),
                dto.getMinPrice()
        );
    }

    private void validateOwner(Store store, Owner owner) {
        if(store.isNotOwnerMatch(owner)){
            throw new EntityNotMatchedException("해당 가게 점주가 아닙니다.");
        }
    }

    private Owner findBy(String email) {
        return ownerRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 오너가 존재하지 않습니다.")
        );
    }
}
