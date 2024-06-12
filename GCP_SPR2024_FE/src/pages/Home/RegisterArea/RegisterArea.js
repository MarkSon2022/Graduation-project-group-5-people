import React from "react";
export default function RegisterArea() {
  return (
    <div className="registerarea sp_top_90">
      <div className="container">
        <div className="row">
          <div
            className="col-xl-7 col-lg-7 col-md-12 col-sm-12 col-12"
            data-aos="fade-up"
          >
            <div className="registerarea__wraper">
              <div className="section__title registerarea__section__title">
                <div className="section__title__button">
                  <div className="default__small__button">Course List</div>
                </div>
                <div className="section__title__heading heading__underline">
                  <h2>
                    Register Your <span>Account </span>Get free access to{" "}
                    <small>60000</small> online course
                  </h2>
                </div>
              </div>
              <div className="registerarea__content">
                <div className="registerarea__video">
                  <div className="video__pop__btn">
                    <a
                      className="video-btn"
                      href="https://www.youtube.com/watch?v=vHdclsdkp28"
                    >
                      {" "}
                      <img loading="lazy" src="img/icon/video.png" alt />
                    </a>
                  </div>
                  <div className="registerarea__para">
                    <p>
                      Learn Something new &amp; Build Your Career From Anywhere
                      In The World
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div
            className="col-xl-5 col-lg-5 col-md-12 col-sm-12 col-12"
            data-aos="fade-up"
          >
            <div className="registerarea__form">
              <div className="registerarea__form__heading">
                <h4>Fill Your Registration</h4>
              </div>
              <form action="#">
                <input
                  className="register__input"
                  type="text"
                  placeholder="Your Name"
                />
                <div className="row">
                  <div className="col-xl-6">
                    <input
                      className="register__input"
                      type="text"
                      placeholder="Email Address"
                    />
                  </div>
                  <div className="col-xl-6">
                    <input
                      className="register__input"
                      type="text"
                      placeholder="Phone"
                    />
                  </div>
                </div>
                <input
                  className="register__input"
                  type="text"
                  placeholder="Address"
                />
                <textarea
                  className="register__input textarea"
                  name="#"
                  id="#"
                  cols={30}
                  rows={10}
                  defaultValue={"Comment"}
                />
                <div className="registerarea__button">
                  <a className="default__button" href="#">
                    Sign Up
                    <i className="icofont-long-arrow-right" />
                  </a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div className="registerarea__img">
        <img
          loading="lazy"
          className="register__1"
          src="img/register/register__1.png"
          alt="register"
        />
        <img
          loading="lazy"
          className="register__2"
          src="img/register/register__2.png"
          alt="register"
        />
        <img
          loading="lazy"
          className="register__3"
          src="img/register/register__3.png"
          alt="register"
        />
      </div>
    </div>
  );
}
