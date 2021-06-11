package com.austral.jibberjabbergateway.security;

import com.austral.jibberjabbergateway.models.AppUser;
import com.austral.jibberjabbergateway.repositories.UserRepository;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    private final UserRepository userRepository;

    public TokenUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((DefaultClaims)authentication.getPrincipal()).get("sub").toString();
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
    }
}
