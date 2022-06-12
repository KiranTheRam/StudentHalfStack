package com.example.studenthalfstack.securty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.studenthalfstack.securty.ApplicationUserRole.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //    This switches security to basic auth, rather than a login screen
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                        .antMatchers("/student/*", "/government/student/*").hasRole(ADMIN.name())
                        .antMatchers("/student/*").hasRole(STUDENT.name())
                        .antMatchers("/government/student/*").hasRole(STUDENTGOV.name())
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    protected UserDetailsManager userDetailsService() {
//        These users are different from any models set up in other classes, like Students.
//        These are users who can (or can't, depending on role) access information through the APIs
        UserDetails kiran = User.builder()
                .username("kiran")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .build();

        UserDetails luke = User.builder()
                .username("luke")
                .password(passwordEncoder.encode("password123"))
                .roles(STUDENTGOV.name())
                .build();

        UserDetails maria = User.builder()
                .username("maria")
                .password(passwordEncoder.encode("password1234"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(kiran, luke, maria);
    }
}
