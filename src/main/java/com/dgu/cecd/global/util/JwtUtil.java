package com.dgu.cecd.global.util;

import com.dgu.cecd.global.config.JwtConfig;
import com.dgu.cecd.global.exception.CustomException;
import com.dgu.cecd.global.exception.ErrorCode;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public Long getUserIdFromToken(String token) {
        log.info("token에서 ID 추출");
        Claims claims = parseClaims(token);
        return getUserIdFromToken(claims);
    }

    private long getUserIdFromToken(Claims claims) {
        return Long.parseLong(claims.get("userId").toString());
    }

    /* 검증 -> valid -> return to parsed jwt */
    private Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //token 유효성 검증
    public Boolean validateToken(String token) {
        try {
            log.info("SECRET KEY :"+ jwtConfig.SECRET_KEY);

            parseClaims(token); // 검증
            return true;
        } catch (SignatureException e) {
            log.info("Sign 오류");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (IllegalArgumentException e) {
            log.info("잘못된 토큰");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (MalformedJwtException e) {
            log.info("토큰 잘림");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (NullPointerException e) {
            log.info("토큰 없음");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 토큰");
            e.printStackTrace();
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        }
    }

}
