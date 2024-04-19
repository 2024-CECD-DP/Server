package com.dgu.cecd.global.config;

import com.dgu.cecd.global.auth.UserDetailsServiceImpl;
import com.dgu.cecd.global.filter.JwtTokenFilter;
import com.dgu.cecd.global.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    private static final String[] AUTH_WHITE_LIST = {
            "/api/v1/user/sign-up", "/api/v1/user/login"
    };

    private static final String[] AUTH_LIST = {
            "/api/v1/user/all"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults()); //TODO

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
                ));

        http.formLogin((form) -> form.disable());
        http.httpBasic((basic) -> basic.disable());

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

//        http.exceptionHandling((exceptionHandling) -> exceptionHandling
//                .authenticationEntryPoint()
//                .accessDeniedHandler()
//        );

        http.authorizeHttpRequests(
                authorize -> authorize
                        //TODO: API 만든 뒤 설정
                        //.requestMatchers(AUTH_LIST).authenticated()
                        .anyRequest().permitAll()
        );
        return http.build();
    }
}
