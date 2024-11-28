package com.rolco.models;

public class UserMessages {
    String messageId;
    String name;
    String message;
    String status;

    public UserMessages(){}

    public UserMessages(String messageId, String name, String message, String status){
        this.messageId = messageId;
        this.name = name;
        this.message = message;
        this.status = status;
    }

    public UserMessages(String name, String message, String status){
        this.name = name;
        this.message = message;
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
