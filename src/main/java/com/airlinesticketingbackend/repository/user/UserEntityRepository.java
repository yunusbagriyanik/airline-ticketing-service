package com.airlinesticketingbackend.repository.user;

import com.airlinesticketingbackend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByUserId(Long userId);
}
