import React from "react";
import {useLocation,useHistory} from 'react-router-dom';
import Lottie from "react-lottie";
import TransactionLottie from "./../assets/transaction_animation.json";
import { Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import OCBC_THEME_COLOR from "../utils/Constants";

 const useStyles = makeStyles({
  submit: {
    color: "white",
    background: OCBC_THEME_COLOR,
    verticalAlign: "bottom",
    fontWeight:"bold",
    "&:hover, &:focus": {
      opacity: 0.8,
      background: OCBC_THEME_COLOR,
    },
  },
});
const Transaction = () => {
  const classes = useStyles();
  const location = useLocation();
  const history = useHistory();
  const defaultOptions = {
    loop: false,
    autoplay: true,
    animationData: TransactionLottie,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };
  const movieMenu = () => {
    history.push({
      pathname: "/movie/menu",
    });
  };
  if (location.state) {
    return (
      <center style={{ padding: "10px" }}>
        <Lottie options={defaultOptions} height={400} width={400} />
        <h2>Transaction Completed </h2>
        <h2>Total Amount Paid: ${Math.abs(location.state.price).toFixed(2)}</h2>
        <h2>Seats Booked: {location.state.movieSeatings.join(", ")}</h2>
        <Button
          type="submit"
          fullWidth
          variant="contained"
          onClick={movieMenu}
          className={classes.submit}
        >
          Home
        </Button>
      </center>
    );
  }
};

export default Transaction;
