package com.sbooking.movie.dto;

import com.sbooking.movie.model.Users;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewUser {
    private Users user;
}
