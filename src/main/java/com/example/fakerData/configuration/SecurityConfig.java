package com.example.fakerData.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
	    http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request -> {
          //request.requestMatchers(HttpMethod.GET).permitAll();
          //request.anyRequest().authenticated();
        	request.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
            request.anyRequest().permitAll();	
        });
        http.httpBasic(Customizer.withDefaults());
        http.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
	    return http.build();
    }
}

