import React, { useEffect, useState } from "react";
import axios from "axios";
import MovieListing from "../component/MovieListing";
import { BACKEND_URL } from "./../utils/Constants";
import Grid from "@material-ui/core/Grid";
import LoadingScreen from "../component/LoadingScreen";

const MovieMenu = () => {
  const [movieDataList, setMovieDataList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const fetchMovieData = async () => {
    try {
      const URL = BACKEND_URL + "/movie/findAllMovies";
      const response = await axios.get(URL);
      if (response.data !== null) {
        console.log(response.data);
        setMovieDataList(response.data);
        setIsLoading(true);
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchMovieData();
  }, []);

  if (!isLoading) return <LoadingScreen />;
  return (
    <center>
      <Grid container item xs={20} spacing={20}>
        {movieDataList &&
          movieDataList.map((movieItem, index) => (
            <MovieListing
              key={index}
              title={movieItem.name}
              duration={movieItem.duration}
              image={movieItem.image_url}
              id={movieItem.movie_id}
            />
          ))}
      </Grid>
    </center>
  );
};

export default MovieMenu;
