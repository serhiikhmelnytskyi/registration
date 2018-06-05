package com.staxter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@SpringBootApplication
public class StaxterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaxterApplication.class, args);
    }

    //	@Bean
//	public MessageSource messageSource() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("/messages/messages");
//		return messageSource;
//	}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();

    }
}
