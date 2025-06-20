package com.chat.controller;
import com.chat.dto.MessageDTO;
import com.chat.service.KafkaConsumerService;
import com.chat.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Queue;

/**
 * @author Miguel Castro
 */
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/chat")
public class ChatController {

    @Autowired
    private KafkaProducerService producerService;

    @Autowired
    private KafkaConsumerService consumerService;

    @PostMapping("/technology/send")
    public ResponseEntity<MessageDTO> sendTechnologyTopicMessage(@RequestBody MessageDTO messageDTO) {
        producerService.sendTechnologyMessage(messageDTO);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @PostMapping("/business/send")
    public ResponseEntity<MessageDTO> sendBusinessTopicMessage(@RequestBody MessageDTO messageDTO) {
        producerService.sendBusinessMessage(messageDTO);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }

    @GetMapping("/technology/messages")
    public ResponseEntity<Queue<MessageDTO>> technologyTopicMessages() {
        Queue<MessageDTO> messages = consumerService.technologyMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/business/messages")
    public ResponseEntity<Queue<MessageDTO>> businessTopicMessages() {
        Queue<MessageDTO> messages = consumerService.businessMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
