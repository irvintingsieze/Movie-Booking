import { AppBar, Button } from "@material-ui/core";
import React from "react";
import { useHistory } from "react-router-dom";
import { useDispatch} from "react-redux";
import Logo from "./../assets/ocbc_logo.png";
import OCBC_THEME_COLOR from "../utils/Constants";
const TopBar = () => {
  const dispatch = useDispatch();
  const userToken = localStorage.getItem("user");
  const history = useHistory();
  const logoutHandler = () =>{
    dispatch({ type: "logout" });
    localStorage.removeItem("user");
    localStorage.removeItem("userid")
    history.push({
      pathname: "/login",
    });
  }
  const goHomeHandler = ()=>{
    history.push({
      pathname:"/movie/menu"
    });
  }
  return (
    <React.Fragment>
      <AppBar
        position="static"
        style={{
          backgroundColor: "white",
          display: "flex",
          flexDirection: "row",
          paddingLeft: "5%",
          paddingRight:"5%",
          justifyContent: "space-between",
        }}
      >
        <img src={Logo} alt="Logo" onClick={goHomeHandler} className="logo"/>
        <div className="row">
        {userToken && <Button style={{ color: OCBC_THEME_COLOR, paddingRight: "25px", paddingLeft:"25px" }}>{userToken}</Button>}
        <Button
          onClick = {logoutHandler}
          style={{ color: OCBC_THEME_COLOR, fontWeight: "bold", paddingRight: "25px", paddingLeft:"25px" }}
        >
          {userToken?"Logout":"Login"}
        </Button>
        </div>
        
      </AppBar>
    </React.Fragment>
  );
};

export default TopBar;
