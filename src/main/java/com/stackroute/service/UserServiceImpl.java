package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsById(user.getId())) {
           throw new UserAlreadyExistsException("User already exists");
        }
            User savedUser = userRepository.save(user);
            if (savedUser == null){
                throw new UserAlreadyExistsException("User Already Exists");
            }
            return savedUser;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
