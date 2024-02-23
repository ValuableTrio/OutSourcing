package org.example.outsourcing.user.repository;

import org.example.outsourcing.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
