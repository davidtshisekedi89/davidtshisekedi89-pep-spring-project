package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;


/**
 * JPA Repository interface for the Message entity
 */

 public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAll();

    // List<Message> findByAccountId(int posted_by);
}
