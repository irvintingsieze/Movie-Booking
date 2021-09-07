import Lottie from "react-lottie";

import LoadingAnimation from "../assets/loading.json";
const LoadingScreen = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: LoadingAnimation,
    rendererSettings: {
      preserveAspectRatio: "xMidYMid slice",
    },
  };
  return (
    <center>
      <Lottie options={defaultOptions} height={400} width={400} />
    </center>
  );
};

export default LoadingScreen;
