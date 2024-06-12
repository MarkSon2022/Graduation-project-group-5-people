import { useFormik } from "formik";
import React, { useEffect, useState } from "react";
import { LoginAction } from "../../redux/actions/AuthAction";
import { useDispatch } from "react-redux";

import { history } from "../../App";
import LoginButton from "./LoginButton";
import { gapi } from "gapi-script";

export default function Login() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const initializeGapi = () => {
    gapi.client.init({
      clientId:
        "23569785076-2qtf8qhfda0b6dote6fhlcqdfnvbg8c9.apps.googleusercontent.com",
      scope: "",
    });
  };

  useEffect(() => {
    gapi.load("client:auth2", initializeGapi);
  });

  const dispatch = useDispatch();
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    onSubmit: (value) => {
      console.log(value);
      const action = LoginAction(value, history);
      dispatch(action);
    },
  });
  return (
    <div>
      <div className="breadcrumbarea">
        <div className="container">
          <div className="row">
            <div className="col-xl-12">
              <div className="breadcrumb__content__wraper" data-aos="fade-up">
                <div className="breadcrumb__title">
                  <h2 className="heading">Log In</h2>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="shape__icon__2">
          <img
            loading="lazy"
            className=" shape__icon__img shape__icon__img__1"
            src="img/herobanner/herobanner__1.png"
            alt="photo"
          />
          <img
            loading="lazy"
            className=" shape__icon__img shape__icon__img__2"
            src="img/herobanner/herobanner__2.png"
            alt="photo"
          />
          <img
            loading="lazy"
            className=" shape__icon__img shape__icon__img__3"
            src="img/herobanner/herobanner__3.png"
            alt="photo"
          />
          <img
            loading="lazy"
            className=" shape__icon__img shape__icon__img__4"
            src="img/herobanner/herobanner__5.png"
            alt="photo"
          />
        </div>
      </div>
      {/* breadcrumbarea__section__end*/}
      {/* login__section__start */}
      <div className="loginarea sp_top_100 sp_bottom_100">
        <div className="container">
          <div className="row">
            <div
              className="col-xl-8 col-md-8 offset-md-2"
              data-aos="fade-up"
            ></div>
            <div
              className="tab-content tab__content__wrapper"
              id="myTabContent"
              data-aos="fade-up"
            >
              <div
                className="tab-pane fade active show"
                id="projects__one"
                role="tabpanel"
                aria-labelledby="projects__one"
              >
                <div className="col-xl-8 col-md-8 offset-md-2">
                  <div className="loginarea__wraper">
                    <div className="login__heading">
                      <h5 className="login__title">Login</h5>
                      Please login with google
                    </div>

                    <div className="login__social__option">
                      <LoginButton onLogin={setIsLoggedIn} />
                    </div>
                  </div>
                </div>
              </div>
              <div
                className="tab-pane fade"
                id="projects__two"
                role="tabpanel"
                aria-labelledby="projects__two"
              ></div>
            </div>
          </div>
          <div className=" login__shape__img educationarea__shape_image">
            <img
              loading="lazy"
              className="hero__shape hero__shape__1"
              src="img/education/hero_shape2.png"
              alt="Shape"
            />
            <img
              loading="lazy"
              className="hero__shape hero__shape__2"
              src="img/education/hero_shape3.png"
              alt="Shape"
            />
            <img
              loading="lazy"
              className="hero__shape hero__shape__3"
              src="img/education/hero_shape4.png"
              alt="Shape"
            />
            <img
              loading="lazy"
              className="hero__shape hero__shape__4"
              src="img/education/hero_shape5.png"
              alt="Shape"
            />
          </div>
        </div>
      </div>
    </div>
  );
}
