package com.smartweb.project.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smartweb.project.jwt.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

	boolean existsByUsername(String string);
}
