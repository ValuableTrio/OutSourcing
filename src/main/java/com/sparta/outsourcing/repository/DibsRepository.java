package com.sparta.outsourcing.repository;


import com.sparta.outsourcing.entity.Dibs;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.Socket;

public interface DibsRepository extends JpaRepository<Dibs, Long> {

    boolean existsByUserAndStore(User user, Store store);

    void deleteByUserAndStore(User user, Store store);
}
