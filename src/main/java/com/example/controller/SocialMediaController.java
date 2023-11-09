package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
@RequestMapping("/")
public class SocialMediaController {


    // private AccountService accountService;
    // private MessageService messageService;


    // @Autowired
    // public SocialMediaController(AccountService accountService, MessageService messageService){
    //     this.accountService = accountService;
    //     this.messageService = messageService;
    // }

    
    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    // Handler to get all messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);

    }

    // Handler to get a message by ID 
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Object> getMessageById(@PathVariable int message_id) {
        Message message = messageService.getMessageById(message_id);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // Handler to delete a message by ID    
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Message> deleteMessageById(@PathVariable("message_id") int message_id) {
        Message deletedMessage = messageService.deleteMessageById(message_id);
        if (deletedMessage != null) {
            return new ResponseEntity<>(deletedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    


}
 
    
      


