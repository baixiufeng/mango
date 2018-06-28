package com.mango.security;

import com.mango.mapper.UserInfoMapper;
import com.mango.model.UserInfo;
import com.mango.model.UserRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserService implements UserDetailsService{

    private UserInfoMapper userInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoMapper.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("该手机号不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role:user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.print(role.getRoleName());
        }
        return new User(user.getPhone(),user.getPassword(),authorities);
    }
}
