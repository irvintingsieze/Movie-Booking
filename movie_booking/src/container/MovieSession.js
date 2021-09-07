import React, { useEffect, useState } from "react";
import axios from "axios";
import { BACKEND_URL } from "../utils/Constants";
import { useHistory, useParams } from "react-router-dom";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import OCBC_THEME_COLOR from "../utils/Constants";
import LoadingScreen from "../component/LoadingScreen";

const useStyles = makeStyles({
  submit: {
    color: "white",
    background: OCBC_THEME_COLOR,
    verticalAlign: "bottom",
    marginTop: 20,
    "&:hover, &:focus": {
      opacity: 0.8,
      background: OCBC_THEME_COLOR,
    },
  },
});

const MovieSession = () => {
  let { id } = useParams();
  const history = useHistory();
  const classes = useStyles();
  const [movieSessions, setMovieSessions] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const BookingSessionHandler = (movie_session_id) => {
    history.push({
      pathname: "/movie/seating/" + movie_session_id,
      state: { movie_session_id: movie_session_id },
    });
  };
  const fetchMovieSessionData = async () => {
    try {
      console.log("HELLO");
      const URL = BACKEND_URL + "/moviesession/movie/" + id;
      console.log(URL);
      const response = await axios.get(URL);
      if (response.data !== null) {
        console.log(response.data);
        setMovieSessions(response.data);
        setIsLoading(true);
      }
    } catch (err) {
      console.error(err);
    }
  };
  useEffect(() => {
    fetchMovieSessionData();
  }, []);
  if (!isLoading) return <LoadingScreen />;
  return (
    <div className="session_container">
      <img
        src={movieSessions.length && movieSessions[0].movie.image_url}
        alt="Cover"
        className="session_img"
      />
      <center className="session_img">
        <h2>{movieSessions.length && movieSessions[0].movie.name}</h2>
        <h4>{movieSessions.length && movieSessions[0].movie.duration}</h4>
        <p className="session_synopsis">
          {movieSessions.length && movieSessions[0].movie.synopsis}
        </p>
        <h3>Sessions</h3>
        {movieSessions &&
          movieSessions.map((session) => (
            <Button
              type="submit"
              fullWidth
              variant="contained"
              onClick={() => BookingSessionHandler(session.movie_session_id)}
              className={classes.submit}
            >
              {session.movie_timing.substring(0, 10)}
            </Button>
          ))}
      </center>
    </div>
  );
};

export default MovieSession;
