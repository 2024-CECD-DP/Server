package com.dgu.cecd.global.util;

import com.dgu.cecd.global.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * AccessToken 생성
 * RefreshToken 생성
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;

    private final Long ACCESS_TOKEN_EXPIRE_TIME =  1000L * 60 * 60 * 24; //24HOUR
    private final Long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 30; //1MONTH

    public String createAccessTokenByUserId(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);// ~ 현재시간 + ACCESS_TOKEN_EXPIRE_TIME

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, String.valueOf(jwtConfig.SECRET_KEY))
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .compact();
    }

    public String createRefreshTokenByUserId(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, String.valueOf(jwtConfig.SECRET_KEY))
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .compact();
    }
}
