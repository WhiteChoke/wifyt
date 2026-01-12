package com.whitechoke.wifyt.dto.participant;

import com.whitechoke.wifyt.enums.UserRoles;

public interface ParticipantInterface {
    Long userId();
    UserRoles role();
    Long chatId();
}
