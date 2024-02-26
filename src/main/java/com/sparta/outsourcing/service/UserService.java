package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.user.*;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserInfoDto signup(SignupUserRequestDto dto) {
        log.info(dto.toString());
        User user = new User(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword())
        );

        return UserInfoDto.of(userRepository.save(user));
    }

    public UserInfoDto login(LoginRequestDto dto) {
        User findUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        return UserInfoDto.of(findUser);
    }

    public UserInfoDto update(Long memberId, UpdateRequestDto dto) {
        User findUser = userRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        findUser.setIntroduce(dto.getIntroduce());

        return UserInfoDto.of(findUser);
    }

    public void delete(Long memberId, DeleteRequestDto dto) {
        User findUser = userRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        userRepository.delete(findUser);
    }
}
