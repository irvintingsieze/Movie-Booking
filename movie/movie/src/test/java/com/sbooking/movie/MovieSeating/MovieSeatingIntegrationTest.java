package com.sbooking.movie.MovieSeating;

import com.sbooking.movie.model.Movie;
import com.sbooking.movie.model.MovieSeating;
import com.sbooking.movie.model.Seats;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieSeatingIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getAllMoviesSeatBySessionId (){
        ResponseEntity<MovieSeating[]> movieSeatings = testRestTemplate.getForEntity("/movie_seating/getSeats?id=1",MovieSeating[].class);
        MovieSeating[] movieSeatingsData = movieSeatings.getBody();
        assertEquals(50,movieSeatingsData.length);
    }

    @Test
    public void getAllMoviesSeatType (){
        ResponseEntity<Seats[]> seats = testRestTemplate.getForEntity("/movie_seating/getAllSeats",Seats[].class);
        List<Seats> seatsList = Stream.of(new Seats(1,"VIP","Maximum comfort with leg rest. Twice the size of the normal seats",19.99),
                new Seats(2,"Normal","Normal Seating",9.99)).collect(Collectors.toList());
        Seats[] seatingData = seats.getBody();
        assertEquals(seatsList.get(0).getSeat_id(),seatingData[0].getSeat_id());
        assertEquals(seatsList.get(0).getSeat_type(),seatingData[0].getSeat_type());
        assertEquals(seatsList.get(0).getDescription(),seatingData[0].getDescription());
        assertEquals(seatsList.get(0).getPrice(),seatingData[0].getPrice());
        assertEquals(seatsList.get(1).getSeat_id(),seatingData[1].getSeat_id());
        assertEquals(seatsList.get(1).getSeat_type(),seatingData[1].getSeat_type());
        assertEquals(seatsList.get(1).getDescription(),seatingData[1].getDescription());
        assertEquals(seatsList.get(1).getPrice(),seatingData[1].getPrice());
    }
}
