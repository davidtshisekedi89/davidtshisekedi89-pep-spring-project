package com.example.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
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

    
    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;


    // Handler for user registration 
    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        Account registeredAccount = accountService.registerUser(account);
        if (registeredAccount != null) {
            return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT); 
        }
    }
    // Handler for Loggin
    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account) {
        Account loggedInAccount = accountService.loginUser(account);
        if (loggedInAccount != null) {
            return new ResponseEntity<>(loggedInAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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
    public ResponseEntity<Integer> deleteMessage(@PathVariable("message_id") int message_id) {
        int rowsModified = messageService.deleteMessageById(message_id);
        if (rowsModified > 0) {
            return new ResponseEntity<>(rowsModified, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    // Handler for creating a new message
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
    if (message.getMessage_text() == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() >= 255) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request for blank or long message text
    }

    Message createdMessage = messageService.createMessage(message);
    if (createdMessage != null) {
        return new ResponseEntity<>(createdMessage, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

    // Handler to update a message by ID    
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int message_id, @RequestBody Message newMessage) {
        if (newMessage.getMessage_text() != null && !newMessage.getMessage_text().isEmpty() && newMessage.getMessage_text().length() <= 255) {
            try {
                int rowsModified = messageService.updateMessage(message_id, newMessage);
                return new ResponseEntity<>(rowsModified, HttpStatus.OK);
            } catch (EntityNotFoundException e) {
                return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST); // 0 rows modified when the message is not found
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        }
    }


    // Handler to get all messages written by a particular user
    // @GetMapping("/accounts/{account_id}/messages")
    // public ResponseEntity<List<Message>> getAllMessagesByUserAccountId(@PathVariable int account_id) {
    //     List<Message> messages = messageService.getMessagesByUser(account_id);

    //     if (!messages.isEmpty()) {
    //         return new ResponseEntity<>(messages, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
    

 
}
 
    
      


