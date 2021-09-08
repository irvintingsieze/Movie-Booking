import React from "react";
import { Redirect, Route } from "react-router-dom";


const UserRoute = ({ component: Component, ...rest }) => {
  const user = localStorage.getItem("user");
  return (
    <Route
      {...rest}
      render={(props) =>
        user? (
          <Component {...props} />
        ) :  (
          <Redirect
            // eslint-disable-next-line react/prop-types
            to={{ pathname: "/login" }}
          />
        )
      }
    />
  );
};

export default UserRoute;