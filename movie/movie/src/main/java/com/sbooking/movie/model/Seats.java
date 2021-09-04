package com.sbooking.movie.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seats {

    @GeneratedValue
    @Id
    private int seat_id;
    private String seat_type;
    private String description;
    private double price;
}
