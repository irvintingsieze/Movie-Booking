package com.sbooking.movie.Movie;

import com.sbooking.movie.model.Movie;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getMovieByIdTest(){
        System.out.println(testRestTemplate);
        ResponseEntity<Movie> movie = testRestTemplate.getForEntity("/movie/1",Movie.class);
        assertEquals(1,movie.getBody().getMovie_id());
        assertEquals("Free Guy",movie.getBody().getName());
        assertEquals("https://www.gv.com.sg/media/imagesresize/img6815.jpg",movie.getBody().getImage_url());
        assertEquals("In \"Free Guy\", a bank teller who discovers he is actually a background player in an open-world video game, decides to become the hero of his own story- one he rewrites himself.",movie.getBody().getSynopsis());
        assertEquals("115 Minutes",movie.getBody().getDuration());
    }

    @Test
    public void getAllMovies (){
        System.out.println(testRestTemplate);
        ResponseEntity<Movie[]> movie = testRestTemplate.getForEntity("/movie/findAllMovies",Movie[].class);
        Movie[] movies = movie.getBody();
        assertEquals(3,movies.length);
    }
}
