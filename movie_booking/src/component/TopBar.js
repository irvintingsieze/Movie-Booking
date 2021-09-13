import { AppBar } from "@material-ui/core";
import React from "react";
import { useHistory } from "react-router-dom";
import Logo from "./../assets/ocbc_logo.png";

const TopBar = () => {
  const history = useHistory();
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
      </AppBar>
    </React.Fragment>
  );
};

export default TopBar;
