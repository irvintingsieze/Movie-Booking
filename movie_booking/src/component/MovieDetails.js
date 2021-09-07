

const MovieDetails = (props) =>{
    return(<div className="movie_details_seating">
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
  </div>);
}

export default MovieDetails;