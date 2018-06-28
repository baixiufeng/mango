package com.mango.service;

import com.mango.model.Users;
import com.mango.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public Users findByName(String username){
        Users users = usersRepository.findByUserName(username);
        return users;
    }

    public void update(Users users){
        usersRepository.save(users);
    }
}
