package com.dgu.cecd.domain.user.application;

import com.dgu.cecd.domain.user.dto.LoginRequestDto;
import com.dgu.cecd.domain.user.dto.LoginResponseDto;
import com.dgu.cecd.domain.user.dto.SignUpRequestDto;
import com.dgu.cecd.domain.user.entity.User;
import com.dgu.cecd.domain.user.dao.UserRepository;
import com.dgu.cecd.global.exception.CustomException;
import com.dgu.cecd.global.exception.ErrorCode;
import com.dgu.cecd.global.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;
    @Transactional
    public void signUp(SignUpRequestDto request) {
        List<User> findUsers = userRepository.findAllByEmail(request.getEmail());
        if(!findUsers.isEmpty()){
            throw new CustomException(ErrorCode.USER_DUPLICATE_RESOURCE);
        }

        User userEntity = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .name(request.getName())
                .build();

        userRepository.save(userEntity);
    }
    public LoginResponseDto login(LoginRequestDto request) { //TODO: String 말고 탕비 정하기
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NAME_NOT_FOUND));

        if (!encoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.PasswordNotFoundException);
        }

        String accessToken = jwtTokenProvider.createAccessTokenByUserId(user.getId());

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }



    public List<User> findUsers() {
        return userRepository.findAll();
    }

}
