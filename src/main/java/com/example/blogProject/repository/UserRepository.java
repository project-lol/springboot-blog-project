package com.example.blogProject.repository;

import com.example.blogProject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // email 로 사용자 정보를 가져옴
}
/*
* 이메일로 사용자를 식별함.
* 즉, 이메일을 사용자의 이름으로 봐도 된다.
* 사용자 정보를 가져오기 위해서는 스프링 시큐리티가 이메일을 전달받아야 한다.
*
* JPA는 메서드를 사용하면 자동으로 쿼리를 생성해준다.
* findByEmail을 사용하면
* FROM users
* WHERE email = #{email}
* 이라는 쿼리문을 생성해준다.
* */