package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // get All messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // get By id
    public Message getMessageById(int id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    // delete messages
    public int deleteMessageById(int message_id) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (messageOptional.isPresent()) {
            messageRepository.deleteById(message_id);
            return 1; // 1 row (message) deleted
        } else {
            return 0; // 0 rows (no message) deleted
        }
    }

    // create messages
    public Message createMessage(Message message) {
        if (userExists(message.getPosted_by()) && isValidMessage(message)) {
            return messageRepository.save(message);
        }

        return null;
    }

    public boolean userExists(Integer userId) {
        // Check if the user exists in the database
        Optional<Message> user = messageRepository.findById(userId);
        return user.isPresent();
    }

    private boolean isValidMessage(Message message) {
        return message.getMessage_text() != null && !message.getMessage_text().isBlank() ;
    }

    // update
    public int updateMessage(int message_id, Message newMessage) {
        Message existingMessage = messageRepository.findById(message_id).orElse(null);
    
        if (existingMessage != null) {
            String newMessageText = newMessage.getMessage_text();
    
            // Check if the new message text is not blank and is not over 255 characters
            if (newMessageText != null && !newMessageText.isEmpty() && newMessageText.length() <= 255) {
                existingMessage.setMessage_text(newMessageText);
                messageRepository.save(existingMessage);
                return 1; // 1 row modified
            } else {
                throw new IllegalArgumentException("Invalid message text");
            }
        } else {
            throw new EntityNotFoundException("Message not found");
        }
    }


    // get message by user id
    // public List<Message> getMessagesByUser(int posted_by) {
    //     return messageRepository.findByAccountId(posted_by);
    // }
}
