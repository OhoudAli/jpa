package com.example.capstone1_jpa.Service;


import com.example.capstone1_jpa.Model.User;
import com.example.capstone1_jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser = userRepository.getById(id);
        if(oldUser == null){
            return false;
        }
        oldUser.setUserName(user.getUserName());
        oldUser.setRole(user.getRole());
        oldUser.setBalance(user.getBalance());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        User user = userRepository.getById(id);
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }

}
