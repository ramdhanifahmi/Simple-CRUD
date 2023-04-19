package com.fahmi.Simple.CRUD.repository;

import com.fahmi.Simple.CRUD.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
