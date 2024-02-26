package org.example.outsourcing.domain.user.service;

import jakarta.persistence.EntityExistsException;
import org.example.outsourcing.domain.user.dto.SignupForm;
import org.example.outsourcing.domain.user.entity.User;
import org.example.outsourcing.domain.user.repository.UserRepository;
import org.example.outsourcing.global.jwt.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtUtil jwtUtil;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserService userService;
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder, jwtUtil);
    }

    @Test
    void 회원_가입() {
        SignupForm signupForm = SignupForm.builder()
                .email("ssss1234@naver.com")
                .password("1234")
                .introduce("테스트 계정입니다.").build();

        String password = passwordEncoder.encode(signupForm.getPassword());

        User user = new User(
                signupForm.getEmail(),
                password,
                signupForm.getIntroduce()
        );

        given(userRepository.save(any(User.class))).willReturn(user);

        User result = userService.signup(signupForm);

        Assertions.assertEquals(user, result);
    }
    @Test
    void 중복_아이디(){
        SignupForm signupForm = SignupForm.builder()
                .email("ssss1234@naver.com")
                .password("1234")
                .introduce("테스트 계정입니다.").build();

        given(userRepository.existsByEmail(signupForm.getEmail())).willReturn(true);


        org.assertj.core.api.Assertions.assertThatThrownBy(()->
                userService.signup(signupForm))
                .isInstanceOf(EntityExistsException.class)
                .hasMessage("중복된 이메일입니다.");
    }
}