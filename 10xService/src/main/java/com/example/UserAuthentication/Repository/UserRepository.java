package com.example.UserAuthentication.Repository;

import com.example.UserAuthentication.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByusername(String username);

    List<User> findByRole(String role);

    @Modifying
    @Transactional
    Optional<User> deleteByusername(String username);
}
