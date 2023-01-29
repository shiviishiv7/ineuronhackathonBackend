package com.hack.hackathon.service;


import com.hack.hackathon.dao.UserRepository;
import com.hack.hackathon.dto.UserDto;
import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findByUserName(String username){

        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else
            throw new UsernameNotFoundException("User not found by Username "+username);

    }


    public User findUserById(String userId) {
//        Optional<User> optionalUser = userRepository.findById("");
        Optional<User> optionalUser = null;

            if(optionalUser.isPresent()){
                return optionalUser.get();
            }else try {
                throw new RecordNotFound("User not found by Id"+userId);
            } catch (RecordNotFound e) {
                throw new RuntimeException(e);
            }

    }

    public UserDto addUser(User user)  {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = this.userRepository.save(user);
        return this.modelMapper.map(save, UserDto.class);
    }

}
