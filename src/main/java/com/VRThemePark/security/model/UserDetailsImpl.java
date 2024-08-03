package com.VRThemePark.security.model;

import com.VRThemePark.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class UserDetailsImpl  {
//public class UserDetailsImpl implements UserDetails {
//    private static final long serialVersionUID = 1L;
//
//    private Integer id;
//
//    private String username;
//
//    private String email;
//
//    @JsonIgnore
//    private String password;
//    private String userStatus;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private Date passwordexpiredTime = new Date();
//
//    private String role;
//
//    public UserDetailsImpl(Integer id, String username, String email, String password,String role) {
//        this.id = id;
//        this.username = username;
//        this.email = email.toLowerCase();
//        this.password = password;
//        this.role = role;
//    }
//
//    public static UserDetailsImpl build(User user) {
//        return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(),user.getRole());
//    }
//
//    public String getUserStatus() {
//        return userStatus;
//    }
//
//    public Date getPasswordexpiredTime() {
//        return passwordexpiredTime;
//    }
//
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
}