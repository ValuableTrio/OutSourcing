package org.example.outsourcing.domain.owner.repository;

import org.example.outsourcing.domain.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
