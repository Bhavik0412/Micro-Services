package com.user.service.service;

import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        //Generate unique Id everyTime
        String randomUserId = UUID.randomUUID().toString();
        user.setUserID(randomUserId);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {

        return userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException( " User given id Not Found "+userId));
    }

    @Override
    public User updateUser(String userId,User user) {
        User existingUSer = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(" User given id Not Found " + userId));
        existingUSer.setName(user.getName());
        existingUSer.setEmail(user.getEmail());
        existingUSer.setAbout(user.getAbout());

        return userRepository.save(existingUSer);
    }
}
