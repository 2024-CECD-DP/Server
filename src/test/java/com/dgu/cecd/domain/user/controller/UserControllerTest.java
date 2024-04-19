package com.dgu.cecd.domain.user.controller;

import com.dgu.cecd.domain.user.dao.UserRepository;
import com.dgu.cecd.domain.user.dto.LoginRequestDto;
import com.dgu.cecd.domain.user.entity.User;
import com.dgu.cecd.domain.user.mock.WithCustomMockUser;
import com.dgu.cecd.domain.user.type.RoleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("/login 시 토큰을 정상적으로 반환합니다. (리프레쉬토큰 임시 null)")
    public void 로그인_반환값_검증() throws Exception {
        //given
        User userEntity = User.builder()
                .name("name")
                .email("ovg07047@naver.com")
                .role(RoleType.USER)
                .password(encoder.encode("1234"))
                .build();

        userRepository.save(userEntity);

        LoginRequestDto request = LoginRequestDto.builder()
                .email("ovg07047@naver.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/api/v1/user/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.refreshToken").isEmpty()) //TODO : 현재는 null
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andDo(print());
    }

    @Test
    @WithCustomMockUser
    public void 전체_사용자_조회() throws Exception {
        //given
        User user = User.builder()
                .name("koko")
                .role(RoleType.USER)
                .email("1234@naver.com")
                .password(encoder.encode("1234"))
                .build();

        userRepository.save(user);

        //expected
        mockMvc.perform(get("/api/v1/user/all")
                        .header("X-AUTH-TOKEN", "aaaaaa")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}