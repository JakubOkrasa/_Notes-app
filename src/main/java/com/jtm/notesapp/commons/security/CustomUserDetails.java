package com.jtm.notesapp.commons.security;

import com.jtm.notesapp.models.UserApp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails extends UserApp implements UserDetails {

    public CustomUserDetails(UserApp userApp) {
        super(userApp);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println();
        getRoles().stream().forEach(r -> System.out.println("getroles: " + r.getRole()));
        System.out.println();
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
                .collect(Collectors.toList());


    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
