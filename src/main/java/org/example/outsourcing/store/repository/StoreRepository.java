package org.example.outsourcing.store.repository;

import org.example.outsourcing.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
