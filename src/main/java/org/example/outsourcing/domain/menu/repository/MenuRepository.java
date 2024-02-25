package org.example.outsourcing.domain.menu.repository;

import org.example.outsourcing.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
