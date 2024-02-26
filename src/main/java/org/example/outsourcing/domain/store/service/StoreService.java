package org.example.outsourcing.domain.store.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.outsourcing.domain.owner.entity.Owner;
import org.example.outsourcing.domain.owner.repository.OwnerRepository;
import org.example.outsourcing.domain.store.dto.StoreInfoForm;
import org.example.outsourcing.domain.store.entity.Store;
import org.example.outsourcing.domain.store.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final OwnerRepository ownerRepository;
    private final StoreRepository storeRepository;

    public Store createStore(String email, StoreInfoForm dto) {
        Owner owner = ownerRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("해당 오너가 존재하지 않습니다.")
        );

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


}
