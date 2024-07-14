package com.service.rating.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    private String ratingId;
    @Column
    private String userId;
    @Column
    private String hotelId;
    @Column
    private int rating;
    @Column
    private String feedback;

}
