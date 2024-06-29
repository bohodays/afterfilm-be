package org.afterfilm.afterfilm.repository;

import org.afterfilm.afterfilm.domain.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
    boolean existsById(Long userId);
    Optional<User> findByRefreshToken(String refreshToken);
    void insertUser(User user);
    void updateUser(User user);
}
