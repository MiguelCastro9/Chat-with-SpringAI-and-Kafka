package com.chat.service;

import com.chat.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Miguel Castro
 */
@Service
public class KafkaConsumerService {

    @Autowired
    private ObjectMapper objectMapper;

    Queue<MessageDTO> technologyTopicMessages = new ConcurrentLinkedQueue<>();
    Queue<MessageDTO> businessTopicMessages = new ConcurrentLinkedQueue<>();

    @KafkaListener(topics = "technology-topic", groupId = "technology-group")
    public void listenTechnologyTopic(String message){
        try {
            MessageDTO dto = objectMapper.readValue(message, MessageDTO.class);
            technologyTopicMessages.add(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "business-topic", groupId = "business-group")
    public void listenBusinessTopic(String message){
        try {
            MessageDTO dto = objectMapper.readValue(message, MessageDTO.class);
            businessTopicMessages.add(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Queue<MessageDTO> technologyMessages () {
        return technologyTopicMessages;
    }

    public Queue<MessageDTO> businessMessages () {
        return businessTopicMessages;
    }
}
