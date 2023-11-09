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
    public Message deleteMessageById(int message_id) {
        Message message = messageRepository.findById(message_id).orElse(null);
        if (message != null) {
            messageRepository.deleteById(message_id);
        }
        return message;
    }

    // create messages
    public Message createMessage(Message message) {
        if (message.getMessage_text() != null && !message.getMessage_text().isEmpty() && message.getMessage_text().length() < 255) {
            return messageRepository.save(message);
        }
        return null;
    }

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
}
