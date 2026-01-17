package com.whitechoke.wifyt.service.impl;

import com.whitechoke.wifyt.security.ChatUserDetails;
import com.whitechoke.wifyt.service.ExtractDataFromAuth;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ExtractDataFromAuthImpl implements ExtractDataFromAuth {

    @Override
    public Long getCurrentUserId() {
        /*
         * If in the future I get an error because of this
         * which I spend more time searching for than writing the project
         * it will be sad :(
         */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ChatUserDetails details = (ChatUserDetails) auth.getPrincipal();
        return details.getId();
    }
}
