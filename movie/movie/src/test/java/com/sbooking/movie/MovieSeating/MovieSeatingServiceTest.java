package com.sbooking.movie.MovieSeating;

import com.sbooking.movie.model.Movie;
import com.sbooking.movie.model.MovieSeating;

import com.sbooking.movie.model.Seats;
import com.sbooking.movie.repository.MovieSeatingRepository;
import com.sbooking.movie.repository.MovieSessionRepository;
import com.sbooking.movie.repository.SeatsRepository;
import com.sbooking.movie.service.MovieSeatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieSeatingService.class)
public class MovieSeatingServiceTest {
    @Autowired
    private MovieSeatingService movieSeatingService;
    @MockBean
    private MovieSeatingRepository movieSeatingRepository;
    @MockBean
    private MovieSessionRepository movieSessionRepository;
    @MockBean
    private SeatsRepository seatsRepository;

    @Test
    public void getAllMovieSeatingBySessionTest(){
        List<MovieSeating> movieSeatings = Stream.of(new MovieSeating(1,false,null,null),
                new MovieSeating(2,false,null,null)).collect(Collectors.toList());
        when(movieSeatingRepository.findAllByMovieSession(1)).thenReturn(movieSeatings);
        assertEquals(2,movieSeatingService.getSeatsBySession(1).size());
    }

    @Test
    public void setOccupiedTest(){
        int[] seatList = {1, 2};
        List<MovieSeating> movieSeatings = Stream.of(new MovieSeating(1,true,null,null),
                new MovieSeating(2,true,null,null)).collect(Collectors.toList());
        movieSeatingService.setMultipleOccupied(seatList);
        verify(movieSeatingRepository,times(1)).findById(1);
        verify(movieSeatingRepository,times(1)).findById(2);
    }

    @Test
    public void getAllSeatsTest(){
        List<Seats> seatsList = Stream.of(new Seats(1,"VIP","For VIP",10.00),
                new Seats(2,"Normal","For Normal",5.00)).collect(Collectors.toList());
        when(seatsRepository.findAll()).thenReturn(seatsList);
        assertEquals(2,movieSeatingService.getAllSeats().size());
    }
}
