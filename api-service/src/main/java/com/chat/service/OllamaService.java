package com.chat.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Miguel Castro
 */
@Service
public class OllamaService {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    public String generateService(String prompt) {
        ChatResponse response = ollamaChatModel.call(
                new Prompt(
                        prompt,
                        OllamaOptions.create()
                                .withModel("tinyllama")
                ));
        return response.getResult().getOutput().getContent();
    }
}
