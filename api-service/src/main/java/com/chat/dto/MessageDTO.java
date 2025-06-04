package com.chat.dto;

import java.time.LocalDateTime;

/**
 * @author Miguel Castro
 */
public class MessageDTO {

    private String username;
    private String topic;
    private String message;
    private String AI_answer;
    private LocalDateTime time;

    public MessageDTO() {
    }

    public MessageDTO(String username, String topic, String message, String AI_answer, LocalDateTime time) {
        this.username = username;
        this.topic = topic;
        this.message = message;
        this.AI_answer = AI_answer;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAIAnswer() {
        return AI_answer;
    }

    public void setAIAnswer(String AI_answer) {
        this.AI_answer = AI_answer;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "username='" + username + '\'' +
                ", topic='" + topic + '\'' +
                ", message='" + message + '\'' +
                ", AIAnswer='" + AI_answer + '\'' +
                ", time=" + time +
                '}';
    }
}
