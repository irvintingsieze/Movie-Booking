import { AppBar, Typography, Button } from "@material-ui/core";
import React from "react";
import "react-sidebar-ui/dist/index.css";
import { Link } from "react-router-dom";
import OCBC_THEME_COLOR from "../utils/Constants";
const TopBar = () => {
  return (
    <React.Fragment>
      <AppBar
        position="static"
        style={{
          backgroundColor: OCBC_THEME_COLOR,
          display: "flex",
          flexDirection: "row",
          paddingLeft: "5%",
          justifyContent: "space-between",
        }}
      >
        <Typography className="no_deco_cashier" variant="h6">
          Bobby
        </Typography>
        <Button
          component={Link}
          to="/login"
          style={{ color: "white", fontWeight: "bold", paddingRight: "5%" }}
        >
          Logout
        </Button>
      </AppBar>
    </React.Fragment>
  );
};

export default TopBar;
