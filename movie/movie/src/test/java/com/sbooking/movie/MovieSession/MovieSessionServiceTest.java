package com.sbooking.movie.MovieSession;


import com.sbooking.movie.model.Movie;
import com.sbooking.movie.model.MovieSession;
import com.sbooking.movie.repository.MovieSessionRepository;
import com.sbooking.movie.service.MovieSessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieSessionService.class)
public class MovieSessionServiceTest {

    @Autowired
    private MovieSessionService movieSessionService;
    @MockBean
    private MovieSessionRepository movieSessionRepository;

    @Test
    public void getOneMovieByIdTest(){
        Movie sangChiMovie = new Movie(1,"Sang Chi","img_url","Synopsis of movie","2 hrs");
        MovieSession movieSession = new MovieSession(1,LocalDateTime.now(),sangChiMovie);
        when(movieSessionRepository.findById(1)).thenReturn(Optional.of(movieSession));
        assertEquals(movieSession,movieSessionService.getMovieSessionById(1));
    }

    @Test
    public void getMovieByMovieIdTest(){
        Movie sangChiMovie = new Movie(1,"Sang Chi","img_url","Synopsis of movie","2 hrs");
        List<MovieSession> movieSession = Stream.of(new MovieSession(1,LocalDateTime.now(),sangChiMovie),new MovieSession(1,LocalDateTime.of(2021, 10, 14, 10, 34),sangChiMovie)).collect(Collectors.toList()); ;
        when(movieSessionRepository.findAllByMovieId(1)).thenReturn(movieSession);
        assertEquals(movieSession,movieSessionService.getAllMovieSessionByMovieId(1));
    }
}
