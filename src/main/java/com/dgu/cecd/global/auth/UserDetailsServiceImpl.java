package com.dgu.cecd.global.auth;

import com.dgu.cecd.domain.user.dao.UserRepository;
import com.dgu.cecd.domain.user.entity.User;
import com.dgu.cecd.global.exception.CustomException;
import com.dgu.cecd.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NAME_NOT_FOUND));


        return new UserDetailsImpl(user);
    }
}
