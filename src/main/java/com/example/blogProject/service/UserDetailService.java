package com.example.blogProject.service;

import com.example.blogProject.domain.User;
import com.example.blogProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 이름(email)으로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }

}
