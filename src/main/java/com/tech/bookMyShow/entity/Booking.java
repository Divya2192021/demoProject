package com.tech.bookMyShow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_Id")
    private int bookingId;
    @Column(name = "user_Id")
    private int userId;
    @Column(name = "movie_Id")
    private int movieId;
    @Column(name = "theater_Id")
    private int theaterId;
    @Column(name = "seats")
    private String seats;
    @Column(name = "amount")
    private int amount;

}
