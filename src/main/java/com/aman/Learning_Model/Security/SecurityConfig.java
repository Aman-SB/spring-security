package com.aman.Learning_Model.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
// tells -> tells spring this class provide configuration to the application
@EnableWebSecurity
// tells -> spring boot to enable web security and
// it give liberty to this class to customize the web security

@EnableMethodSecurity
// to give access to there api to the roles based
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
//any reqest the application get authenticated by default

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // above to remove the cookie

//        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
//        basic authenticate to spring security
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

//        {noop} -> it kept the password same as it is

        UserDetails user1 = User.withUsername("user1")
                .password("{noop}Password1")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}PassAdmin")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user1 , admin);
    }
}
