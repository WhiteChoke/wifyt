package com.whitechoke.wifyt.dto.message;

public interface MessageInterface {
    Long senderId();
    Long chatId();
    String message();
}
