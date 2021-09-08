import React, { useState } from "react";
import axios from "axios";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import popcorn_icon from "./../assets/popcorn.png";
import OCBC_THEME_COLOR, { BACKEND_URL } from "../utils/Constants";
import { useHistory, Redirect } from "react-router-dom";
import { useDispatch } from "react-redux";

const useStyles = makeStyles((theme) => ({
  root: {
    height: "100vh",
  },
  image: {
    backgroundImage: "url(https://source.unsplash.com/random)",
    backgroundRepeat: "no-repeat",
    backgroundColor:
      theme.palette.type === "light"
        ? theme.palette.grey[50]
        : theme.palette.grey[900],
    backgroundSize: "cover",
    backgroundPosition: "center",
  },
  paper: {
    margin: theme.spacing(8, 4),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  form: {
    width: "100%",
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
    color: "white",
    background: OCBC_THEME_COLOR,
    "&:hover, &:focus": {
      opacity: 0.8,
      background: OCBC_THEME_COLOR,
    },
  },
}));

const Login = () => {
  const user = localStorage.getItem("user");
  const dispatch = useDispatch();
  const history = useHistory();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const LoginHandler = async() => {

    try{
      console.log(username + password)
      const req_url = BACKEND_URL + "/user/login?username=" + username + "&password=" + password;
      const res = await axios.post(req_url);
      console.log(res)
      console.log(res.data);
      if (res.data){
        dispatch({ type: "login", payload: {username: res.data.username, id:res.data.id}});
        history.push({
          pathname: "/movie/menu",
        });
      }
    }catch(exception){
      alert(exception);
    }
    
  };
  const classes = useStyles();
  if (user) return <Redirect to='/movie/menu'></Redirect>
  else{
    return (
      <Grid container component="main" className={classes.root}>
        <CssBaseline />
        <Grid item xs={false} sm={4} md={7} className={classes.image} />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <div className={classes.paper}>
            <img src={popcorn_icon} alt="icon" />
            <Typography component="h1" variant="h5">
              Sign in
            </Typography>
            <form className={classes.form} noValidate>
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoFocus
                onChange={(e)=>setUsername(e.target.value)}
              >{username}</TextField>
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                onChange={(e)=>setPassword(e.target.value)}
              >{password}</TextField>
              <Button
                //type="submit"
                fullWidth
                variant="contained"
                onClick={LoginHandler}
                className={classes.submit}
              >
                Sign In
              </Button>
            </form>
          </div>
        </Grid>
      </Grid>
    );
  }
 
};

export default Login;
