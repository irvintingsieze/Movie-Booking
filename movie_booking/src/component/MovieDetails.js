

const MovieDetails = (props) =>{
    return(
    <div className="movie_details_seating">
    <img
      src={props.movieDetails.image_url}
      className="movie_img"
      alt="Movie cover"
    />
    <div>
      <h3>{props.movieDetails.name}</h3>
      <p>{props.movieDetails.duration}</p>
      <p>{props.movieTime}</p>
    </div>
    <hr className="hrMargins"/>
    <div >
      <div className="margins">
        <div className="circle" ></div>
        <div className="text_details">Normal Seats</div>
      </div>
      <div className="margins">
        <div className="square"></div>
        <div className="text_details">VIP Seats</div>

      </div>
      <div className="margins">
        <div className="circle_clicked"></div>
        <div className="text_details">Selected Seats</div>
      </div>
      <div className="margins">
        <div className="circle_unavail"></div>
        <div className="text_details">Booked Seats</div>
      </div>
    </div>
  </div>);
}

export default MovieDetails;