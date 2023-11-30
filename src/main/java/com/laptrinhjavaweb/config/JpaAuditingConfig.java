package com.laptrinhjavaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
	
	@Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
	//AuditorAware lay username
    public static class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public String getCurrentAuditor() {
        	//authentication se kiem tra neu authentication != null
        	//isAuthenticated() la da dang nhap rui, luc do moi co data de get ra.
        	//neu chua co return null
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return null;
            }
            return authentication.getName();
        }
    }
}
