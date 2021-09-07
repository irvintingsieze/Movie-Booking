import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Login from "./container/LoginPage";
import MovieMenu from "./container/MovieMenu";
import MovieSeating from "./container/MovieSeating";
import Transaction from "./container/Transaction";
import MovieSession from "./container/MovieSession";
function App() {
  return (
    <>
      <Router>
        <Route exact path="/login" component={Login} />
        <Route path="/movie/menu" component={MovieMenu} />
        <Route path="/movie/session/:id" component={MovieSession} />
        <Route path="/movie/seating/:id" component={MovieSeating} />
        <Route path="/movie/transaction" component={Transaction} />
      </Router>
    </>
  );
}

export default App;
