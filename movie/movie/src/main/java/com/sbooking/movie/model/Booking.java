package com.sbooking.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
    @Id
    @GeneratedValue
    private int booking_id;
    @OneToOne(targetEntity = MovieSeating.class,cascade = CascadeType.ALL)
    @JoinColumn(name="mv_seating_id")
    private MovieSeating movieSeating;
    @OneToOne(targetEntity = Users.class,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Users users;

}