package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.user.*;
import com.sparta.outsourcing.entity.PasswordHistory;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.repository.PasswordHistoryRepository;
import com.sparta.outsourcing.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.sparta.outsourcing.config.SessionConst.SESSION_USER;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordHistoryRepository passwordHistoryRepository;

    public UserInfoDto signup(SignupUserRequestDto dto) {
        Optional<User> findEmail = userRepository.findByEmail(dto.getEmail());

        if (findEmail.isPresent()) {
            throw new RuntimeException("Duplicate email");
        }

        User user = new User(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword())
        );

        return UserInfoDto.of(userRepository.save(user));
    }

    public UserInfoDto login(LoginUserRequestDto dto, HttpSession session) {
        User findUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        session.setAttribute(SESSION_USER.name(), dto.getEmail());

        return UserInfoDto.of(findUser);
    }

    public UserInfoDto updatePassword(Long memberId, UpdateUserPasswordRequestDto dto) {
        User findUser = userRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getOriginPassword(), findUser.getPassword())) {
            throw new RuntimeException("Invalid access");
        }

        if (passwordEncoder.matches(dto.getNewPassword(), findUser.getPassword())) {
            throw new RuntimeException("Can't change to current password");
        }

        List<PasswordHistory> histories = passwordHistoryRepository
                .findByUserIdOrderByCreatedAtDesc(memberId, Pageable.ofSize(3));

        Optional<PasswordHistory> findHistory = histories.stream()
                .filter(history -> passwordEncoder.matches(dto.getNewPassword(), history.getPassword()))
                .findFirst();

        if (findHistory.isPresent()) {
            throw new RuntimeException("최근 3회 사용했던 비밀번호는 사용할 수 없습니다.");
        }

        passwordHistoryRepository.save(new PasswordHistory(
                findUser,
                findUser.getPassword()
        ));

        findUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        return UserInfoDto.of(findUser);
    }

    public UserInfoDto update(Long memberId, UpdateUserRequestDto dto) {
        User findUser = userRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        findUser.setIntroduce(dto.getIntroduce());

        return UserInfoDto.of(findUser);
    }

    public void delete(Long memberId, DeleteUserRequestDto dto) {
        User findUser = userRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        userRepository.delete(findUser);
    }
}
