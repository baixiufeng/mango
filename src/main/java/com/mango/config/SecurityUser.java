package com.mango.config;

import com.mango.model.SysRole;
import com.mango.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class SecurityUser extends Users implements UserDetails{

    public SecurityUser(Users sysUser) {
        if (sysUser != null) {
            this.setId(sysUser.getId());
            this.setDob(sysUser.getDob());
            this.setEmail(sysUser.getEmail());
            this.setPassword(sysUser.getPassword());
            this.setSRoles(sysUser.getSysRoles());
            this.setName(sysUser.getName());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<SysRole> userRoles = this.getSysRoles();
        if (userRoles != null) {
            for (SysRole role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getName();
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
