package com.service.hotel.services;

import com.service.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

     Hotel saveHotel(Hotel hotel);

     Hotel getHotelById(String hotelId);

     List<Hotel> getAllHotel();

     Hotel updateHotel(String hotelId, Hotel hotel);
}
