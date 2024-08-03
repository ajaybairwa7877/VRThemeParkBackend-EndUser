package com.VRThemePark.security;

import com.VRThemePark.repository.UserDetailsRepository;
import com.VRThemePark.security.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

import com.VRThemePark.entity.User;

@Service
public class CustomUserDetailService {
//public class CustomUserDetailService implements UserDetailsService {

	
//	@Autowired
//	private PasswordEncoder encoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userDetailsRepository.findByUsername(username);
//        if (user==null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        System.out.println("user details : " + user.toString());
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//
////        return new org.springframework.security.core.userdetails.User(
////                user.getUsername(),
////                //encoder.encode(user.getPassword()),
////                user.getPassword(),
////                authorities
////        );
//
//
//        return UserDetailsImpl.build(user);
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//
//        // loading user details from database
//        com.VRThemePark.entity.User byUsername = userDetailsRepository.findByUsername(username);
//
//        User user = null;
//
//        if(username.equals(this.username)) {
//        	user = new User(this.username, encoder.encode(this.password), authorities);
//        }
//        return user;
//    }
}
