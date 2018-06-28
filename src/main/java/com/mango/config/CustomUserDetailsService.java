package com.mango.config;

import com.mango.model.Users;
import com.mango.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("customUserDetailsService")
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findByName(username);
        if (user == null){
            throw new UsernameNotFoundException("userName"+ username+"not found");
        }
        SecurityUser securityUser = new SecurityUser(user);
        return securityUser;
    }

}
