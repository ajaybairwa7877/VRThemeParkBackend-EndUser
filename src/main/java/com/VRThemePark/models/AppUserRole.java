package com.VRThemePark.models;

//import org.springframework.security.core.GrantedAuthority;

//public enum AppUserRole implements GrantedAuthority {
public enum AppUserRole {
  ROLE_ADMIN, ROLE_CLIENT;

  public String getAuthority() {
    return name();
  }

}
