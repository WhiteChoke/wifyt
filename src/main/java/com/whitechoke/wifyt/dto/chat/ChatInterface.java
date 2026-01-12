package com.whitechoke.wifyt.dto.chat;

import com.whitechoke.wifyt.enums.ChatType;

import java.util.List;

public interface ChatInterface {
    String name();
    ChatType chatType();
    List<Long> participantList();
}
