package com.solRoom.solspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean // 암호화
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/css/**", "/album/**", "/js/**", "/bmp/**", "/jpg/**").permitAll()
                        .requestMatchers("/", "/login","/loginForm","newMember","/checkDuplicateEmail").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "MEMBER")
                        .anyRequest().authenticated()
                );
        http
                .formLogin((auth)->auth.loginPage("/login")
                        .loginProcessingUrl("/loginForm")
                        .permitAll());


        http
                .csrf((auth)->auth.disable());


        return http.build();
    }
}
