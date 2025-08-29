
package com.blue_economy.model;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class CustomUserDetails implements UserDetails {

    private final com.blue_economy.model.User user;

    public CustomUserDetails(com.blue_economy.model.User user) {
        this.user = user;
    }

    // Expose your User entity if needed
    public com.blue_economy.model.User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming user.getRole() returns a String like "ROLE_USER"
        return Collections.singletonList(() -> user.getRole());
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }


    public String getUsername() {
        // Use email as username
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // implement your logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // implement your logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // implement your logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // implement your logic if needed
    }
}
