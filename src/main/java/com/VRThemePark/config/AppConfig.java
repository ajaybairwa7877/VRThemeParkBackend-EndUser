package com.VRThemePark.config;

import com.VRThemePark.entity.HeaderSection;
import com.VRThemePark.models.NavItems;
import com.VRThemePark.repository.ApiRepository;
import com.VRThemePark.repository.UserDetailsRepository;
import com.VRThemePark.services.AuthenticateService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class AppConfig {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ApiRepository apiRepository;


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user=User.builder().username("prathiksha").password
//                (passwordEncoder().encode("abc")).roles("ADMIN").build();
//
//        UserDetails user1=User.builder().username("pooja").password
//                (passwordEncoder().encode("123")).roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user,user1);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }

    @Bean
    public void insertUser(){
        com.VRThemePark.entity.User user = new com.VRThemePark.entity.User();
        user.setUsername("Ajay");
//        user.setPassword("abc");
        user.setPassword("abc");
        user.setEmail("ajay@gmail.com");
        user.setRole("ADMIN");

        userDetailsRepository.save(user);
    }


    @Bean
    public void insertHeaderSectionData(){
        HeaderSection headerSection = new HeaderSection();
        //headerSection.setId();
        headerSection.setLogoImage("C:\\Users\\Admin\\Downloads\\FINAL DESIGN UI July 2024 (4).jpg");
        headerSection.setNavlink1("BASE");
        headerSection.setNavlink2("CORE");
        headerSection.setNavlink3("COORDINATE");
        headerSection.setNavlink4("VOID");
        headerSection.setButtonText1("CONTACT");
        headerSection.setButtonText2("BLOG");

        apiRepository.save(headerSection);

    }


}
