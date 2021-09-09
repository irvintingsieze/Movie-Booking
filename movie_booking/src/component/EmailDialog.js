import React, {useState} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import SeatSelected from './SeatsSelected';
import {useHistory} from "react-router-dom";
import axios from "axios";
import { BACKEND_URL } from '../utils/Constants';

const EmailDialog =(props)=> {
  const history = useHistory();
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [isValidEmail, setIsValidEmail] = useState(true);

  const sendEmailToUser = async ()=>{
    const req_url = BACKEND_URL + "/booking/email_confirmation";
    const data = {
      name:name,
      email:email,
      price:Math.abs(props.price).toFixed(2),
      movieName:props.movieName,
      movieTime:props.movieTime,
      seatList:props.listOfSelectedSeats,
    }
    try{
      const res = await axios.post(req_url,data);
      if (res.status===200)
      return true;
    }catch(exception){
      console.log(exception);
    }
    return false
  }

  const confirmBookingHandler = async()=>{
    if (email.length && name.length){
      props.updateOccupiedSeating();
      const successReq = await sendEmailToUser();
      if (successReq){
        props.setOpen();
        setIsValidEmail(true);
        history.push({
          pathname: "/movie/transaction",
          state: {price:props.price, movieSeatings:props.listOfSelectedSeats}
        });
      }
      setIsValidEmail(false);
    }
  }

  return (
    <div>
      <Dialog open={props.isOpen} onClose={props.setOpen} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Confirm Purchase</DialogTitle>
        <DialogContent>
            <DialogContentText>
            A movie booking confirmation will be sent to your email once the transaction
            is completed.
          </DialogContentText>
          <SeatSelected numNormal = {props.numNormal} numVIP={props.numVIP} price={props.price} listOfSelectedSeats = {props.listOfSelectedSeats}/>
          {name.length? <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Name"
            fullWidth
            onChange={(e)=>setName(e.target.value)}
          >{name}</TextField>:
          <TextField
          error
          id="filled-error-helper-text"
          label="Name"
          helperText="This field cannot be empty"
          onChange={(e)=>setName(e.target.value)}
          fullWidth
        />
          }
          {!email.length?<TextField
          error
          id="filled-error-helper-text"
          label="Email"
          helperText="This field cannot be empty"
          onChange={(e)=>setEmail(e.target.value)}
          fullWidth
        />:!isValidEmail?<TextField
          error
          id="filled-error-helper-text"
          label="Email"
          helperText="Invalid email address provided"
          onChange={(e)=>setEmail(e.target.value)}
          fullWidth
        />:
            <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Email Address"
            type="email"
            onChange={(e)=>setEmail(e.target.value)}
            fullWidth
          >{email}</TextField>
          }
          
        </DialogContent>
        <DialogActions>
          <Button onClick={props.setOpen} color="primary">
            Cancel
          </Button>
          <Button onClick={confirmBookingHandler} color="primary">
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}

export default EmailDialog;