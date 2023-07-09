package com.student.logisticscompany.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/employees/activate").permitAll()
                        .requestMatchers("/employees/activate-details").permitAll()
                        .requestMatchers("/employees").hasAnyAuthority("ADMIN")
                        .requestMatchers("/offices").hasAnyAuthority("ADMIN", "EMPLOYEE", "DELIVERY")
                        .requestMatchers("/company").hasAnyAuthority("ADMIN")
                        .requestMatchers("/parcels/new").hasAnyAuthority("ADMIN", "EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .formLogin(formLoginConfigurer -> formLoginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll())
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/login")
                        .permitAll())
                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                        .accessDeniedPage("/unauthorized"));
        return http.build();
    }
}
