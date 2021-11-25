package com.tech.bookMyShow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
////@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Theaters")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="theater_id")
    private int id;
    @Column(name="theater_name",length = 225)
    @NotEmpty(message = "*Please provide a theater name")
    private String name;
    @Column(name="theater_city",length = 225)
    private String city;
    @Column(name="seat_no")
    private int seatNo;
    @Column(name="amount")
    private int amount;

}
