package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;

/**
 * JPA Repository interface for the Account entity
 */

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByUsername(String username);
    Account findByUsernameAndPassword(String username, String password);
}
