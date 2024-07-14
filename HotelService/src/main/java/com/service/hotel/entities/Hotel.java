package com.service.hotel.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    private String hotelId;

    @Column(name = "HotelName")
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "AboutHotel")
    private String about;


}
