package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.owner.*;
import com.sparta.outsourcing.entity.Owner;
import com.sparta.outsourcing.entity.OwnerAccountState;
import com.sparta.outsourcing.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    public OwnerInfoDto signup(SignupOwnerRequestDto dto) {
        Owner owner = new Owner(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword())
        );

        return OwnerInfoDto.of(ownerRepository.save(owner));
    }

    public OwnerInfoDto login(LoginOwnerRequestDto dto) {
        Owner findOwner = ownerRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findOwner.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        return OwnerInfoDto.of(findOwner);
    }

    public OwnerInfoDto update(Long ownerId, UpdateOwnerRequestDto dto) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (!passwordEncoder.matches(dto.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        return OwnerInfoDto.of(ownerRepository.save(owner));
    }

    public OwnerInfoDto updateBusinessNumber(Long ownerId, UpdateOwnerBusinessNumberRequestDto dto) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (!passwordEncoder.matches(dto.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        owner.setBusinessNumber(dto.getBusinessNumber());
        owner.setState(OwnerAccountState.CERTIFIED);
        return OwnerInfoDto.of(ownerRepository.save(owner));
    }

    public void delete(Long ownerId, DeleteOwnerRequestDto dto) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (!passwordEncoder.matches(dto.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        ownerRepository.delete(owner);
    }
}
