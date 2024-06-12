import React from "react";
import { NavLink } from "react-router-dom/cjs/react-router-dom";
export default function HeroBanner() {
  return (
    <div className="herobannerarea herobannerarea__2 herobannerarea__online__course">
      <div className="container">
        <div className="row">
          <div
            className="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12"
            data-aos="fade-up"
          >
            <div className="herobannerarea__content__wraper">
              <div className="herobannerarea__title">
                <div className="herobannerarea__small__title">
                  <span>Education Solution</span>
                </div>
                <div className="herobannerarea__title__heading__2 herobannerarea__title__heading__3">
                  <h2>
                    Your Awesome<span> Online </span>Course
                  </h2>
                </div>
              </div>
              <div className="herobannerarea__text herobannerarea__text__2">
                <p>
                  Gain real-world skills that you can immediately apply to your
                  profession. <br />
                  We focus on practical, actionable content that makes a
                  difference.
                </p>
              </div>
              <div className="hreobannerarea__button__2">
                <a className="default__button" href="/course">
                  View Courses
                </a>
                <NavLink
                  className="default__button hreobannerarea__button__3"
                  to="/test-quiz"
                >
                  Demo Test
                </NavLink>
              </div>
            </div>
          </div>
          <div
            className="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12"
            data-aos="fade-up"
          >
            <div className="herobanner__video registerarea__video">
              <img
                loading="lazy"
                src="img/herobanner/herobanner__video.jpg"
                alt="Video Banner"
              />
              <div className="video__pop__btn">
                <a
                  className="video-btn"
                  href="https://www.youtube.com/watch?v=vHdclsdkp28"
                >
                  {" "}
                  <img loading="lazy" src="img/icon/video.png" alt />
                </a>
              </div>
            </div>
          </div>
          <div className="herobannerarea__icon__2">
            <img
              loading="lazy"
              className="herobanner__common__img herobanner__img__1"
              src="img/herobanner/herobanner__1.png"
              alt="photo"
            />
            <img
              loading="lazy"
              className="herobanner__common__img herobanner__img__2"
              src="img/herobanner/herobanner__2.png"
              alt="photo"
            />
            <img
              loading="lazy"
              className="herobanner__common__img herobanner__img__3"
              src="img/herobanner/herobanner__3.png"
              alt="photo"
            />
            <img
              loading="lazy"
              className="herobanner__common__img herobanner__img__4"
              src="img/herobanner/herobanner__4.png"
              alt="photo"
            />
            <img
              loading="lazy"
              className="herobanner__common__img herobanner__img__5"
              src="img/herobanner/herobanner__5.png"
              alt="photo"
            />
          </div>
        </div>
      </div>
    </div>
  );
}
