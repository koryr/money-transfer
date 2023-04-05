package com.pezesha.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pezesha.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Data
public class UserPrincipal implements UserDetails {
    private String username;
    @JsonIgnore
    private String password;

    public UserPrincipal(
            String username,
            String password
    ) {
        this.username = username;
        this.password = password;
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
