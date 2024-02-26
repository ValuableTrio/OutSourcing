package org.example.outsourcing.domain.user.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.outsourcing.domain.user.dto.SigninForm;
import org.example.outsourcing.domain.user.dto.SignupForm;
import org.example.outsourcing.domain.user.entity.User;
import org.example.outsourcing.domain.user.repository.UserRepository;
import org.example.outsourcing.global.jwt.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User signup(SignupForm dto) {
        emailExistsValidation(dto.getEmail());

        String password = passwordEncoder.encode(dto.getPassword());

        User user = new User(
                dto.getEmail(),
                password,
                dto.getIntroduce()
        );

        return userRepository.save(user);
    }

    public String signin(SigninForm dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        passwordValidation(dto, user);

        return jwtUtil.createToken(dto.getEmail());
    }

    private void passwordValidation(SigninForm dto, User user) {
        if (user.isNotPasswordMatch(passwordEncoder, dto.getPassword())) {
            throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
        }
    }


    private void emailExistsValidation(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EntityExistsException("중복된 이메일입니다.");
        }
    }

}
