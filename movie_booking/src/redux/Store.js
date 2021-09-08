import { createStore } from "redux";

const loginReducer = (state = { isLoggedIn: false, username:null }, action) => {
  
    if (action.type === "login") {
        localStorage.setItem("user",action.payload.username);
        localStorage.setItem("userid",action.payload.id);
        return {
            username:action.payload,
            isLoggedIn: true,
        };
    }
    if (action.type === "logout") {
        localStorage.removeItem("user");
        localStorage.removeItem("userid");
        return {
            username:null,
            isLoggedIn: false,
        };
    }
    return state;
};

const store = createStore(loginReducer);

export default store;