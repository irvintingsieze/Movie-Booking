import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import MovieMenu from "./container/MovieMenu";
import MovieSeating from "./container/MovieSeating";
import Transaction from "./container/Transaction";
import MovieSession from "./container/MovieSession";
import Home from "./container/Home";
import TopBar from "./component/TopBar";
function App() {
  return (
    <>
      <Router>
      <Route exact path= "/" component= {Home}/>
        <Route path = "/movie" component = {TopBar}/>
        <Route path="/movie/menu" component={MovieMenu} />
        <Route path="/movie/session/:id" component={MovieSession} />
        <Route path="/movie/seating/:id" component={MovieSeating} />
        <Route path="/movie/transaction" component={Transaction} />
      </Router>
    </>
  );
}

export default App;
