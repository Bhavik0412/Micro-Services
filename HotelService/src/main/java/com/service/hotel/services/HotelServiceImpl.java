package com.service.hotel.services;

import com.service.hotel.Exception.ResourceNotFoundException;
import com.service.hotel.entities.Hotel;
import com.service.hotel.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).
                orElseThrow(()->new ResourceNotFoundException("hotel is not available for given id"+hotelId));
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotel) {
        Hotel existingHotel = hotelRepository.findById(hotelId).
                orElseThrow(() -> new ResourceNotFoundException("hotel is not available for given id" + hotelId));

        existingHotel.setName(hotel.getName());
        existingHotel.setLocation(hotel.getLocation());
        existingHotel.setAbout(hotel.getAbout());

        return hotelRepository.save(existingHotel);
    }

}
