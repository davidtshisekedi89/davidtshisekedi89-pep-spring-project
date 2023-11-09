package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public Account registerUser(Account account) {
        if (isValidAccount(account) && !usernameExists(account.getUsername())) {
            return accountRepository.save(account);
        }
        return null;
    }

    public Account loginUser(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

    private boolean isValidAccount(Account account) {
        return account.getUsername() != null && !account.getUsername().isEmpty() &&
        account.getPassword() != null && account.getPassword().length() >= 4;
    }

    private boolean usernameExists(String username) {
        return accountRepository.findByUsername(username) != null;
    }
}
