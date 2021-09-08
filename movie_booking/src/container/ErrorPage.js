import Lottie from "react-lottie";
import ErrorLottie from "./../assets/lottie_error.json";
import { Button } from "@material-ui/core";
const ErrorPage = (props) =>{
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: ErrorLottie,
        rendererSettings: {
          preserveAspectRatio: "xMidYMid slice",
        },
      };
    return (
        <center style={{ padding: "10px" }}>
          <Lottie options={defaultOptions} height={400} width={400} />
          <h2>Unauthorised Access</h2>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            onClick={props.movieMenu}
            className={props.styling}
          >
            Home
          </Button>
        </center>
    );
}

export default ErrorPage;