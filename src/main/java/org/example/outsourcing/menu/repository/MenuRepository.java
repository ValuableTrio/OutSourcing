package org.example.outsourcing.menu.repository;

import org.example.outsourcing.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
