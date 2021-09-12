import React, { useEffect, useState, useRef } from "react";
import { useParams, Prompt } from "react-router-dom";
import SockJsClient from "react-stomp";
import { BACKEND_CONCURRENCY_URL, BACKEND_URL } from "../utils/Constants";
import axios from "axios";
import LoadingScreen from "../component/LoadingScreen";
import MovieDetails from "../component/MovieDetails";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import OCBC_THEME_COLOR from "../utils/Constants";
import ErrorDialog from "../component/ErrorDialog";
import EmailDialog from "../component/EmailDialog";
import { Beforeunload } from "react-beforeunload";

const useStyles = makeStyles({
  root: {
    maxWidth: 280,
    margin: 70,
  },
  submit: {
    color: "white",
    background: OCBC_THEME_COLOR,
    verticalAlign: "bottom",
    "&:hover, &:focus": {
      opacity: 0.8,
      background: OCBC_THEME_COLOR,
    },
  },
});

const MovieSeating = () => {
  const classes = useStyles();
  const { id } = useParams();
  let clientRef = useRef(null);
  const [allSelectedSeats, setAllSelectedSeats] = useState([]);
  const [openPopup, setOpenPopup] = useState(false);
  const [isOpenEmailPopup, setIsOpenEmailPopup] = useState(false);
  const [movieSeatings, setMovieSeatings] = useState([]);
  const [seatPricing, setPricing] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [movieDetails, setMovieDetails] = useState({});
  const [movieTime, setMovieTime] = useState();
  const [clickedSeats, setClickedSeats] = useState(false);
  const [listOfSelectedSeats, setListOfSelectedSeats] = useState([]);
  const [price, setPrice] = useState(0.0);
  const [numNormal, setNumNormal] = useState(0);
  const [numVIP, setNumVIP] = useState(0);
  const [updateOccupied, setUpdateOccupied] = useState([]);

  const onSeatIdReceived = (seatList) => {
    setAllSelectedSeats(seatList);
    console.log(seatList);
  };

  const sendSeatSelection = async (newSelection) => {
    try {
      await clientRef.sendMessage("/app/seat_booking", newSelection);
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  const fetchMovieSeatingData = async () => {
    try {
      const URL = BACKEND_URL + "/movie_seating/getSeats?id=" + id;
      const response = await axios.get(URL);
      if (response.data !== null) {
        const res = response.data;
        const movieSeatList = [];
        let data;
        setMovieDetails(res[0].movieSessions.movie);
        setMovieTime(res[0].movieSessions.movie_timing.substring(0, 10));
        for (let i = 0; i < res.length; i++) {
          data = {
            id: res[i].movie_seating_id,
            seatid: res[i].seats.seat_id,
            isOccupied: res[i].occupied,
            isSelected: false,
          };
          movieSeatList.push(data);
        }
        setMovieSeatings(movieSeatList);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const updateOccupiedSeating = async () => {
    try {
      await axios.patch(BACKEND_URL + "/movie_seating/occupy", {
        seatList: updateOccupied,
      });
    } catch (exception) {
      console.log(exception);
    }
  };

  const bookSeatsHandler = async () => {
    if (updateOccupied.length) {
      setIsOpenEmailPopup(!isOpenEmailPopup);
    } else {
      setOpenPopup(!openPopup);
    }
  };

  const fetchMovieSeatData = async () => {
    try {
      const seats_url = BACKEND_URL + "/movie_seating/getAllSeats";
      const seatsData = await axios.get(seats_url);
      if (seatsData.data != null) {
        const seatList = [],
          seatData = seatsData.data;
        for (let j = 0; j < seatData.length; j++) {
          const { seat_type, description, ...rest } = seatData[j];
          seatList.push(rest);
        }
        setPricing(seatList);
        setIsLoading(true);
      }
    } catch (err) {
      console.error(err);
    }
  };

  const setClicked = (index, id) => {
    movieSeatings[index].isSelected = !movieSeatings[index].isSelected;
    setMovieSeatings(movieSeatings);
    setClickedSeats(!clickedSeats);
    sendSeatSelection(id);
    if (listOfSelectedSeats.includes(index + 1)) {
      const i = listOfSelectedSeats.indexOf(index + 1);
      const j = updateOccupied.indexOf(id);
      if (i > -1) {
        listOfSelectedSeats.splice(i, 1);
        setListOfSelectedSeats(listOfSelectedSeats);
        updateOccupied.splice(j, 1);
        setUpdateOccupied(updateOccupied);
      }
    } else {
      setListOfSelectedSeats([...listOfSelectedSeats, index + 1]);
      setUpdateOccupied([...updateOccupied, id]);
    }
    if (movieSeatings[index].isSelected) {
      setPrice(price + seatPricing[movieSeatings[index].seatid - 1].price);
      if (index > 20 && index < 31) setNumVIP(numVIP + 1);
      else setNumNormal(numNormal + 1);
    } else {
      if (index > 20 && index < 31) setNumVIP(numVIP - 1);
      else setNumNormal(numNormal - 1);
      setPrice(price - seatPricing[movieSeatings[index].seatid - 1].price);
    }
  };

  const handlePopup = () => {
    setOpenPopup(!openPopup);
  };
  const handleEmailPopup = () => {
    setIsOpenEmailPopup(!isOpenEmailPopup);
  };

  useEffect(() => {
    fetchMovieSeatingData();
    fetchMovieSeatData();
  }, []);

  if (!isLoading) return <LoadingScreen />;

  return (
    <div className="session_container">
      <div className="left_container_seating">
        <MovieDetails movieDetails={movieDetails} movieTime={movieTime} />
        <div className="rectangle">SCREEN</div>
        <div className="set_center">
          <div className="grid_seating">
            {movieSeatings &&
              movieSeatings.map((seat, index) => {
                if (seat.isOccupied) {
                  return (
                    <div key={index} className="circle_unavail">
                      {index + 1}
                    </div>
                  );
                }
                if (allSelectedSeats.includes(seat.id) && !seat.isSelected) {
                  return (
                    <div
                      key={index}
                      className={
                        seat.seatid === 2
                          ? "circle_clicked_by_others"
                          : "square_selected_by_others"
                      }
                    >
                      {index + 1}
                    </div>
                  );
                }
                if (seat.seatid === 2) {
                  return (
                    <div
                      key={index}
                      className={seat.isSelected ? "circle_clicked" : "circle"}
                      onClick={() => setClicked(index, seat.id)}
                    >
                      {index + 1}
                    </div>
                  );
                } else {
                  return (
                    <div
                      key={index}
                      className={seat.isSelected ? "square_selected" : "square"}
                      onClick={() => setClicked(index, seat.id)}
                    >
                      {index + 1}
                    </div>
                  );
                }
              })}
          </div>
        </div>
      </div>
      <hr />
      <div className="right_container_seating">
        <h3 className="text">Seating Selection</h3>
        <h4>Seat Selected: </h4>
        <p>
          {listOfSelectedSeats.length ? listOfSelectedSeats.join(", ") : "None"}
        </p>
        <h4>
          Total normal seats selected:{" "}
          <span className="text_span">{numNormal}</span>
        </h4>
        <h4>
          Total VIP seats selected: <span className="text_span">{numVIP}</span>
        </h4>
        <h4>
          Total Price:{" "}
          <span className="text_span">${Math.abs(price).toFixed(2)}</span>
        </h4>
        <Button
          type="submit"
          fullWidth
          variant="contained"
          onClick={bookSeatsHandler}
          className={classes.submit}
        >
          Book
        </Button>
        <ErrorDialog
          isOpen={openPopup}
          setOpen={handlePopup}
          content="You have not selected any seats!"
        />
        <EmailDialog
          isOpen={isOpenEmailPopup}
          setOpen={handleEmailPopup}
          numNormal={numNormal}
          numVIP={numVIP}
          price={price}
          listOfSelectedSeats={listOfSelectedSeats}
          updateOccupiedSeating={updateOccupiedSeating}
          movieName={movieDetails.name}
          movieTime={movieTime}
        />
        <SockJsClient
          url={BACKEND_CONCURRENCY_URL + "/stomp"}
          topics={["/topic/seatid", "seat_booking", "disconnect"]}
          onConnect={() => {
            clientRef.sendMessage("/app/seat_booking", -1);
            console.log("Connected to websocket");
          }}
          onDisconnect={() => {
            console.log("Disconnected from websocket");
          }}
          onMessage={onSeatIdReceived}
          ref={(client) => {
            clientRef = client;
          }}
        />
      </div>
      <Prompt
        message={() => {
          clientRef &&
            clientRef.sendMessage("/app/disconnect", listOfSelectedSeats);
        }}
      />
      {listOfSelectedSeats.length && (
        <Beforeunload
          onBeforeunload={(event) => {
            event.preventDefault();
            clientRef &&
              clientRef.sendMessage("/app/disconnect", listOfSelectedSeats);
            clientRef.onDisconnect();
            return false;
          }}
        />
      )}
    </div>
  );
};

export default MovieSeating;
