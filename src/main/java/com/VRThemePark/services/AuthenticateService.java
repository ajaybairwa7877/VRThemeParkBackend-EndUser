package com.VRThemePark.services;

import com.VRThemePark.entity.JwtBlacklist;

import com.VRThemePark.entity.User;
import com.VRThemePark.models.JwtRequest;
import com.VRThemePark.models.MessageResponse;
import com.VRThemePark.repository.JwtBlacklistRepository;
import com.VRThemePark.repository.UserDetailsRepository;
import com.VRThemePark.security.CustomUserDetailService;
import com.VRThemePark.security.JWTHelper;
import com.VRThemePark.security.model.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service
public class AuthenticateService {

//	@Autowired
//	private AuthenticationManager authenticationManager;

//	@Autowired
//	private JWTHelper jwtHelper;
//
//	@Autowired
//	public JwtBlacklistRepository jwtBlacklistRepository;
//
//
//	@Autowired
//	CustomUserDetailService userDetailService ;
//
//	//@Autowired
//	//private PasswordEncoder encoder;
//
//	private static final Logger log = LoggerFactory.getLogger(AuthenticateService.class);
//
//	public MessageResponse createAuthentication(JwtRequest authenticationRequest) {
//		try {
//			UserDetailsImpl userDetails = (UserDetailsImpl) userDetailService.loadUserByUsername(authenticationRequest.getUsername());
//			log.info("Details received from database : " + userDetails.toString());
//
//			if (userDetails == null) {
//				log.error("user not found : {} ", authenticationRequest.getUsername());
//				//return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), false,null, "!!!!! User not Found !!!!!"));
//				return new MessageResponse(HttpStatus.UNAUTHORIZED.value(), false,null, "!!!!! User not Found !!!!!");
//			}
//
//			try {
//				this.doAuthenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//				log.info("user details authenticate successfully");
//
//				if(!encoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())){
//					log.error("Invalid user password : {} ", authenticationRequest.getPassword());
//					//return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), false,null, "!!!!! Invalid User Password !!!!!"));
//
//					return new MessageResponse(HttpStatus.UNAUTHORIZED.value(), false,null, "!!!!! Invalid User Password !!!!!");
//
//				}
//			} catch (BadCredentialsException e) {
//				log.error("!!!!! Invalid login details !!!!! : {} ", authenticationRequest.toString());
//				//return ResponseEntity.badRequest().body(new MessageResponse(HttpStatus.UNAUTHORIZED.value(), false,null, "!!!!! Invalid User Details !!!!!"));
//
//				return new MessageResponse(HttpStatus.UNAUTHORIZED.value(), false,null, "!!!!! Invalid User Details !!!!!");
//			}
//
//			String jwtToken = jwtHelper.generateToken(userDetails);
//			log.info("JWT token send to user : " + jwtToken);
//
//			//return ResponseEntity.ok().body(new MessageResponse(HttpStatus.OK.value(), true,jwtToken , "Login Successfully"));
//			//return ResponseEntity.status(200).body(new MessageResponse(HttpStatus.OK.value(), true,jwtToken, "Login Successfully"));
//			return new MessageResponse(HttpStatus.OK.value(), true,jwtToken, "Login Successfully");
//		} catch (Exception e) {
//			log.error("!!!!! Invalid login details !!!!! : {} ", authenticationRequest.toString());
//			//return ResponseEntity.status(500).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,null,
//					//"!!!!! Invalid login details !!!!!"));
//
//			return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,null,
//					"!!!!! Invalid login details !!!!!");
//		}
//	}
//
//	private void doAuthenticate(String username, String password) {
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
//		try {
//			authenticationManager.authenticate(authentication);
//		} catch (BadCredentialsException e) {
//			throw new BadCredentialsException(" Invalid Username or Password  !!");
//		}
//	}
//
//	@ExceptionHandler(BadCredentialsException.class)
//	public String exceptionHandler() {
//		return "Credentials Invalid !!";
//	}
//
//	public boolean signout(HttpServletRequest request) {
//		log.info("signout request received");
//		try {
//			final String authorizationHeader = request.getHeader("Authorization");
//			String jwt = null;
//			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//
//				UserDetailsImpl loginUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//				log.info("logged user id : {}" ,loginUser.getId());
//
//				jwt = authorizationHeader.substring(7);
//				JwtBlacklist blacklist = jwtBlacklistRepository.findByToken(jwt);
//
//				if(blacklist!=null){
//					log.error("Token is already Blacklisted : {}",jwt);
//				}else {
//					log.info("sign-out token : {} ", jwt);
//					JwtBlacklist jwtBlacklist = new JwtBlacklist();
//					jwtBlacklist.setToken(jwt);
//
//					jwtBlacklistRepository.save(jwtBlacklist);
//				}
//
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			log.error("Error occurred while sign-out the application : {}", e.getMessage());
//			e.printStackTrace();
//			throw new RuntimeException("Error in sign out application");
//		}
//	}
}
