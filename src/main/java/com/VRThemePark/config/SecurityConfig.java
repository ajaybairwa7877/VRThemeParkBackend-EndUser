package com.VRThemePark.config;

import com.VRThemePark.security.JWTAthenticationEntryPoint;
//import com.VRThemePark.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JWTAthenticationEntryPoint point;
//    @Autowired
//    private JWTAuthenticationFilter filter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // configuration
//        http.csrf(csrf->csrf.disable())
//                .cors(cors->cors.disable())
//                .authorizeHttpRequests(auth->
//                        auth.requestMatchers("").authenticated()
//                        .requestMatchers("/user").permitAll().anyRequest()
//                        .authenticated())
//                        .exceptionHandling(ex->ex.authenticationEntryPoint(point))
//                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // configuration
//        http.csrf(csrf->csrf.disable())
//                .cors(cors->cors.disable())
//                .authorizeHttpRequests(auth->
//                        auth.requestMatchers("/user/**").authenticated()
//                                .requestMatchers("/auth/login").permitAll().anyRequest()
//                                .authenticated())
//                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
//                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Allow all origins, you can customize this
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


}
