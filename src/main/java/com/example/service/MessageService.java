package com.example.service;

import java.util.List;
import java.util.Optional;

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
}
