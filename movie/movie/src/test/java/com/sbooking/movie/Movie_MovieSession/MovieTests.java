package com.sbooking.movie.Movie_MovieSession;

import com.sbooking.movie.model.Movie;
import com.sbooking.movie.repository.MovieRepository;
import com.sbooking.movie.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieService.class)
public class MovieTests {
    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void getAllMovieTest(){
        when(movieRepository.findAll()).thenReturn(Stream.of(new Movie(1,"Sang Chi","img_url","Synopsis of movie","2 hrs"),
                new Movie(2,"Sang Chi part 2","img_url 2","Synopsis of Sang Chi movie","3 hrs")).collect(Collectors.toList()));
        assertEquals(2,movieService.getAllMovies().size());
    }

    @Test
    public void getOneMovieByIdTest(){
        Movie sangChiMovie = new Movie(1,"Sang Chi","img_url","Synopsis of movie","2 hrs");
        when(movieRepository.findById(1)).thenReturn(Optional.of(sangChiMovie));
        assertEquals(sangChiMovie,movieService.getMovieById(1));
    }
}
