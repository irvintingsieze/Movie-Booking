import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import OCBC_THEME_COLOR from "../utils/Constants";
import { useMediaQuery } from '@material-ui/core';
import { useHistory } from "react-router-dom";

const useStyles = makeStyles({
  root: {
    maxWidth: "20%",
    marginLeft: "9%",
  },
  root_none:{
    maxWidth:"60%",
    marginLeft:"20%",
    marginTop:"5%"
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

const MovieListing = (props) => {
  const classes = useStyles();
  const history = useHistory();

  const BookingHandler = () => {
    history.push({
      pathname: "/movie/session/" + props.id,
      state: { movieid: props.id },
    });
  };

  return (
    <Card className={useMediaQuery('(min-width:880px)')?classes.root:classes.root_none}>
      <CardActionArea>
        <CardMedia
          component="img"
          alt="Contemplative Reptile"
          //height="140"
          image={props.image}
          title="Contemplative Reptile"
        />
        <CardContent>
          <Typography gutterBottom variant="h6" component="p">
            {props.title}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            {props.duration}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button
          type="submit"
          fullWidth
          variant="contained"
          onClick={BookingHandler}
          className={classes.submit}
        >
          Book
        </Button>
      </CardActions>
    </Card>
  );
};

export default MovieListing;
