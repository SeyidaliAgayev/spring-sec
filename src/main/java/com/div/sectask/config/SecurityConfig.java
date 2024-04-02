package com.div.sectask.config;

import com.div.sectask.service.UserService;
import com.div.sectask.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers(HttpMethod.POST, "/signup").permitAll();
            requests.requestMatchers(HttpMethod.POST, "/login").permitAll();
//            requests.requestMatchers(HttpMethod.GET, "/users/{id}").permitAll();
//            requests.requestMatchers(HttpMethod.POST, "/users/authentication").anonymous();
//            requests.requestMatchers(HttpMethod.GET, "/users/refreshed-token").permitAll();
//            requests.requestMatchers(HttpMethod.GET, "/users/oauth-authentication").anonymous();
//
//            requests.requestMatchers(HttpMethod.POST, "/products").hasAnyAuthority("CREATE_PRODUCT");
//            requests.requestMatchers(HttpMethod.GET, "/products").authenticated();
//            requests.requestMatchers(HttpMethod.GET, "/products/test").hasAnyAuthority("CREATE_PRODUCT");
//            requests.requestMatchers(HttpMethod.PUT, "/products").hasAnyAuthority("UPDATE_ALL_PRODUCT");
//            requests.requestMatchers(HttpMethod.DELETE, "/products").hasRole("ADMIN");
//            requests.requestMatchers(HttpMethod.PATCH, "/products").hasAuthority("CHANGE_PRODUCT_STATUS");
//            requests.requestMatchers("/roles").hasRole("ADMIN");
//
//            requests.requestMatchers(HttpMethod.GET, "/logout").authenticated();

            requests.anyRequest().denyAll();
        });

        return http.build();



    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}

