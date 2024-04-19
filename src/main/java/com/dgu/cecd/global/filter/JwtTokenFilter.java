package com.dgu.cecd.global.filter;

import com.dgu.cecd.global.auth.UserDetailsServiceImpl;
import com.dgu.cecd.global.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    private final String HEADER_NAME = "Authorization";
    private final String HEADER_PRE_FIX = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER_NAME);

        if (authorizationHeader != null && authorizationHeader.startsWith(HEADER_PRE_FIX)) {
            String token = authorizationHeader.substring(7);

            try {
                jwtUtil.validateToken(token);

                Long userId = jwtUtil.getUserIdFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                log.info("(토큰 검증 실패)유효하지 않은 토큰입니다.");
                SecurityContextHolder.clearContext();
                filterChain.doFilter(request, response);
                e.printStackTrace();
            }
        } else {
            log.info("토큰이 없습니다.");
            filterChain.doFilter(request,response);
        }
        filterChain.doFilter(request,response);
    }

//    private void printHeaders(final HttpServletRequest request) {
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            log.info("Header: {} = {}", headerName,request.getHeader(headerName));
//        }
//    }
}
