package com.sbooking.movie.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class EmailMessage {

    String name;
    String email;
    String price;
    String movieName;
    String movieTime;
    int [] seatList;
}
