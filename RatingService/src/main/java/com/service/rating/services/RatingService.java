package com.service.rating.services;

import com.service.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRating();

    List<Rating> getRatingByHotelId(String hotelId);

    List<Rating> getRatingByUserId(String userId);
}
