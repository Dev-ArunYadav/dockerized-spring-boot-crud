package com.crud.repository;

import com.crud.entity.User;
import com.crud.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
    List<User> findByRoleIn(List<UserRole> roles);
    boolean existsByRole(UserRole admin);
}
