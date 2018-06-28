package com.mango.repository;

import com.mango.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long>{

    Users findByUserName(String username);
}
