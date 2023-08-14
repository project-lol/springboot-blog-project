package com.example.blogProject.config;

import com.example.blogProject.service.UserDetailService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;


    // 스프링 시큐리티 기능 비활성화
    /*
     * 스프링 시큐리티의 모든 기능을 사용하지 않게 설정하는 코드이다.
     * 일반적으로 정적 리소스에 설정한다.(이미지, HTMl 파일)
     * 정적 리소스만 스프링 시큐리티 사용을 비활성화하는 데 static 하위 경로에 있는
     * 리소스와 h2의 데이터를 확인하는 데 사용하는 h2-console 하위 url을 대상으로
     * ignoring() 메서드를 사용한다.
     * */

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }


    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    /*
     * 특정 HTTP 요청에 대해 웹 기반 보안을 구성한다.
     * 이 메서드에서 인증/인가 및 로그인, 로그아웃 관련 설정을 할 수 있다.
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/articles")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()
                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}