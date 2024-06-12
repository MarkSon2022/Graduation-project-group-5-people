import axios from "axios";
import GoogleLogin from "react-google-login";
import { BASE_URL } from "../../data/const";
import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
const clientId =
  "23569785076-2qtf8qhfda0b6dote6fhlcqdfnvbg8c9.apps.googleusercontent.com";

export default function LoginButton({ onLogin }) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const history = useHistory();
  useEffect(() => {
    const user = localStorage.getItem("USER");
    if (user) {
      setIsLoggedIn(true);
    }
  }, []);

  const onSuccess = (response) => {
    console.log("LOGIN SUCCESS");

    axios
      .post(BASE_URL + "api/v1/users/sign-in", response.profileObj)
      .then((response) => {
        if (response.status === 200) {
          localStorage.setItem("USER", JSON.stringify(response.data));
          setIsLoggedIn(true);
          onLogin(true);
          history.push("/course");
        }
      });
  };

  const onFailure = (response) => {
    console.log("LOGIN FAILURE");
  };

  return (
    <div style={{ marginTop: "30px" }} id="signInButton">
      <GoogleLogin
        clientId={clientId}
        buttonText="Login with Google"
        onSuccess={onSuccess}
        onFailure={onFailure}
        cookiePolicy={"single_host_origin"}
        isSignedIn={false}
      />
    </div>
  );
}
