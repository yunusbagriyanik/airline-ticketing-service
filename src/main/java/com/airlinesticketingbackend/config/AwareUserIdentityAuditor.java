package com.airlinesticketingbackend.config;

import com.airlinesticketingbackend.auth.security.TokenizedUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AwareUserIdentityAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && (auth.getPrincipal() instanceof TokenizedUser)) {
            TokenizedUser user = (TokenizedUser) auth.getPrincipal();
            return Optional.of(user.getId().toString());
        }
        return Optional.empty();
    }
}
