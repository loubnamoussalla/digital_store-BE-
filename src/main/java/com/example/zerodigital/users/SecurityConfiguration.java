package com.example.zerodigital.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private  UserService userService;

    @Autowired
    private AccessDeniedHandle accessDeniedHandle;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth", "/media/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandle)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    //     AuthenticationManager replaces configure(AuthenticationManagerBuilder)
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    auth.authenticationProvider(authenticationProvider());
    return auth.build();
}




    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(userService.passwordEncoder());
        return provider;
    }
}
