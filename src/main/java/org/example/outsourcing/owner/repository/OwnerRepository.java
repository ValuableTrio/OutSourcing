package org.example.outsourcing.owner.repository;

import org.example.outsourcing.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
