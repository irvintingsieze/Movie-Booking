package com.sbooking.movie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieSession {
    @GeneratedValue
    @Id
    private int movie_session_id;
    private LocalDateTime movie_timing;
    @ManyToOne(targetEntity = Movie.class,cascade = CascadeType.ALL)
    @JoinColumn(name="mv_id",referencedColumnName = "movie_id")
    private Movie movie;
}
