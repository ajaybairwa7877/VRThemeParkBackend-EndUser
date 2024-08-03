package com.VRThemePark.config;

import com.VRThemePark.models.JwtRequest;
import com.VRThemePark.models.JwtResponse;
import com.VRThemePark.models.MessageResponse;
import com.VRThemePark.security.CustomUserDetailService;
import com.VRThemePark.security.JWTHelper;
import com.VRThemePark.services.AuthenticateService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticateService authenticateService;

//    @PostMapping("/login")
//    public MessageResponse login(@RequestBody JwtRequest request) {
//        //return ResponseEntity.status(200).body(new MessageResponse(HttpStatus.OK.value(), true,authenticateService.createAuthentication(request), ""));
//        return authenticateService.createAuthentication(request);
//        //return ResponseEntity.status(200).body(new MessageResponse(HttpStatus.OK.value(), true,authenticateService.createAuthentication(request), "Login Successfully"));
//        //return ResponseEntity.status(200).body(new MessageResponse(HttpStatus.OK.value(), true,"{token : "+ "token" +"}", "Login Successfully"));
//    }
//
//    @PostMapping("/signout")
//    public ResponseEntity<?> signout(HttpServletRequest request) {
//        if(authenticateService.signout(request)) {
//            return ResponseEntity.ok()
//                    .body(new MessageResponse(HttpStatus.OK.value(), false, null, "Successfully Logout"));
//        }else {
//            return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, null, "Error while logout"));
//        }
//    }


}
