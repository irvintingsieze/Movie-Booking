const SeatSelected = (props) => {
  return (
    <div>
      <h4>Seat Selected: </h4>
      <p>
        {props.listOfSelectedSeats.length
          ? props.listOfSelectedSeats.join(", ")
          : "None"}
      </p>
      <h4>
        Total normal seats selected:{" "}
        <span className="text_span">{props.numNormal}</span>
      </h4>
      <h4>
        Total VIP seats selected:{" "}
        <span className="text_span">{props.numVIP}</span>
      </h4>
      <h4>
        Total Price:{" "}
        <span className="text_span">${Math.abs(props.price).toFixed(2)}</span>
      </h4>
    </div>
  );
};
export default SeatSelected;
