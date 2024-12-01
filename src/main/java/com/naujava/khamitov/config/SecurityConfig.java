package com.naujava.khamitov.config;

import com.naujava.khamitov.model.constant.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration", "/login", "/logout").permitAll()
                        .requestMatchers("/swagger-ui/**", "/rest/**", "/user/**", "/exercise/remove/**",
                                "/exercise/form/**")
                        .hasRole(Role.ADMIN.name())
                        .requestMatchers("/answer/**", "/exercise/**")
                        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                        .requestMatchers("/").hasAuthority(Role.GUEST.name())
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/main", true));

        return http.build();
    }
}
