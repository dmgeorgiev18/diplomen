package com.project.motohotel.configuration;


import com.project.motohotel.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.headers().frameOptions().disable();

        security.authorizeHttpRequests((request) ->
                request
                .requestMatchers("/login","/loginError")
                        .anonymous()
                        .requestMatchers("/subscriptions","/subscriptions/**").authenticated()
                        .requestMatchers("/admin","/admin/**","/subscriptions/edit/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
        );
        security.formLogin().loginPage("/login")
                .failureUrl("/loginError").defaultSuccessUrl("/");
        security.userDetailsService((UserDetailsService) userService);
        security.logout().logoutSuccessUrl("/");

        return security.build();
    }

}
