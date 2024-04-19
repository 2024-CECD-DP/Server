package com.dgu.cecd.domain.user.controller;

import com.dgu.cecd.domain.user.dto.LoginRequestDto;
import com.dgu.cecd.domain.user.dto.LoginResponseDto;
import com.dgu.cecd.domain.user.dto.SignUpRequestDto;
import com.dgu.cecd.domain.user.dto.UserDto;
import com.dgu.cecd.domain.user.entity.User;
import com.dgu.cecd.domain.user.application.UserService;
import com.dgu.cecd.domain.user.type.RoleType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Valid SignUpRequestDto request) {
        userService.signUp(request);
        //TODO: 회원가입은 리턴 값 FE와 상의되어야함. 일단 void로 진행
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto request) {

        return ResponseEntity.ok().body(userService.login(request));
    }


    //TODO : Response Body에 데이터를 못 불러옴.
    @GetMapping("/all")
    @PreAuthorize("USER")
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> users = userService.findUsers();
        List<UserDto> collect = users.stream()
                .map(u -> new UserDto(u.getName()))
                .collect(toList());

        return ResponseEntity.ok(collect);
    }
}
