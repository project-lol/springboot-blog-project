package com.example.blogProject.service;

import com.example.blogProject.domain.User;
import com.example.blogProject.dto.AddUserRequest;
import com.example.blogProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode((dto.getPassword())))
                .build()).getId();
    }
}

/*
패스워드를 저장할 때 시큐리티를 설정하며
패스워드 인코딩용으로 등록한 빈을 사용해서 암호화한 후에 저장한다.
* */
