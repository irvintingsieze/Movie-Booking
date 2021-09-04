package com.sbooking.movie.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    @GeneratedValue
    @Id
    private int movie_id;
    private String name;
    private String image_url;
    private String synopsis;
    private String duration;


}
