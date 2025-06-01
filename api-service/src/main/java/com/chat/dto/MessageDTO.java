package com.chat.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private String username;
    private String topic;
    private String message;
    private String AIprompt;
    private LocalDateTime time;

    public MessageDTO() {
    }

    public MessageDTO(String username, String topic, String message, String AIprompt, LocalDateTime time) {
        this.username = username;
        this.topic = topic;
        this.message = message;
        this.AIprompt = AIprompt;
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

    public String getAIprompt() {
        return AIprompt;
    }

    public void setAIprompt(String AIprompt) {
        this.AIprompt = AIprompt;
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
                ", AIprompt='" + AIprompt + '\'' +
                ", time=" + time +
                '}';
    }
}
