package com.jt.security_database_rest.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jt.security_database_rest.model.Student;
import com.jt.security_database_rest.repository.StudentRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(request -> request
                .requestMatchers("/add").permitAll()
                .requestMatchers("/").authenticated()
                .anyRequest().authenticated())
                // .formLogin(Customizer.withDefaults());
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(StudentRepo studentRepo) {
        return username -> {

            Student student = studentRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Name Not Found ."));

            return User.builder().username(username).password(student.getPassword())
                    .roles("USER").build();
        };
    }

    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {

    // DaoAuthenticationProvider dAuthenticationProvider = new
    // DaoAuthenticationProvider();
    // dAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    // dAuthenticationProvider.setUserDetailsService(myUserDetailsService);
    // return dAuthenticationProvider;

    // }
}
