package com.chat.service;

import com.chat.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * @author Miguel Castro
 */
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OllamaService ollamaService;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendTechnologyMessage(MessageDTO messageDTO){
        try {
            if (messageDTO.getMessage().contains("@Ollama")) {
                String message = messageDTO.getMessage().replaceFirst("@Ollama", "");
                String AIAnswer = ollamaService.generateService(message);
                messageDTO.setAIAnswer(AIAnswer);
            }
            messageDTO.setTime(LocalDateTime.now());
            String json = objectMapper.writeValueAsString(messageDTO);
            kafkaTemplate.send(messageDTO.getTopic(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendBusinessMessage(MessageDTO messageDTO){
        try {
            if (messageDTO.getMessage().contains("@Ollama")) {
                String message = messageDTO.getMessage().replaceFirst("@Ollama", "");
                String AIAnswer = ollamaService.generateService(message);
                messageDTO.setAIAnswer(AIAnswer);
            }
            messageDTO.setTime(LocalDateTime.now());
            String json = objectMapper.writeValueAsString(messageDTO);
            kafkaTemplate.send(messageDTO.getTopic(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
