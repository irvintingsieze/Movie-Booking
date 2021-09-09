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
public class MovieSeating {
    @GeneratedValue
    @Id
    private int movie_seating_id;
    private boolean isOccupied;
    @Column(columnDefinition = "boolean default false")
    private boolean isSelected;
    @ManyToOne(targetEntity = MovieSession.class,cascade = CascadeType.ALL)
    @JoinColumn(name="mv_session_id",referencedColumnName = "movie_session_id")
    private MovieSession movieSessions;
    @ManyToOne(targetEntity = Seats.class,cascade = CascadeType.ALL)
    @JoinColumn(name="seats_id")
    private Seats seats;
}
