package com.aman.Learning_Model.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
// tells -> tells spring this class provide configuration to the application
@EnableWebSecurity
// tells -> spring boot to enable web security and
// it give liberty to this class to customize the web security
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//any reqest the application get authenticated by default
//        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
//        basic authenticate to spring security
        return http.build();
    }
}