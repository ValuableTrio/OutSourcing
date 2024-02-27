package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.user.*;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sparta.outsourcing.config.SessionConst.SESSION_USER;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public UserInfoDto login(LoginUserRequestDto dto, HttpServletRequest request) {
        User findUser = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new RuntimeException("Username or Password is incorrect");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER.name(), dto.getEmail());

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
