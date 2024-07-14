package com.user.service.controller;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.externalServices.HotelService;
import com.user.service.externalServices.RatingService;
import com.user.service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private RatingService ratingService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByID(@PathVariable String userId){
        User userById = userService.getUserById(userId);
        List<Rating> ratings = ratingService.getRating(userId);
        ratings.forEach(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        });
        userById.setRating(ratings);
        return ResponseEntity.ok(userById);

    }
//This Below code when we use Rest Template

//        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/" + userId, Rating[].class);
//        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
//        ratings.forEach(rating -> {
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            rating.setHotel(hotel);
//        });
//
//        userById.setRating(ratings);


    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers = userService.getAllUser();
        allUsers.forEach(user -> {
            List<Rating> ratings = ratingService.getRating(user.getUserID());
            ratings.forEach(rating -> {
                Hotel hotel = hotelService.getHotel(rating.getHotelId());
                rating.setHotel(hotel);
            });
            user.setRating(ratings);
        });
        return ResponseEntity.ok(allUsers);

    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser( @PathVariable  String userId, @RequestBody User user){
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

}
