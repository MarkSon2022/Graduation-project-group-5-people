import React from "react";
export default function AboutArea() {
  return (
    <div className="aboutarea__2 aboutarea__4 sp_bottom_30 sp_top_30">
      <div className="container">
        <div className="row">
          <div
            className="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12"
            data-aos="fade-up"
          >
            <div className="about__right__wraper__2 about__right__wraper__4">
              <div className="educationarea__img" data-tilt>
                <img
                  loading="lazy"
                  className="aboutimg__4__img__1"
                  src="img/about/about_13.png"
                  alt="about"
                />
                <img
                  loading="lazy"
                  className="aboutimg__4__img__2"
                  src="img/about/about_12.png"
                  alt="about"
                />
              </div>
            </div>
          </div>
          <div
            className="col-xl-6 col-lg-6 col-md-12 col-sm-12 col-12"
            data-aos="fade-up"
          >
            <div className="aboutarea__content__wraper">
              <div className="section__title__2">
                <div className="section__small__title">
                  <span>Education Solution</span>
                </div>
                <div className="section__title__heading__2 section__title__heading__3 heading__fontsize__2">
                  <h2>Welcome to the online Learning Center</h2>
                </div>
              </div>
              <div className="aboutarea__para aboutarea__para__2">
                <p>
                  25+Contrary to popular belief, Lorem Ipsum is not simply
                  random text roots in a piece of classical Latin literature
                  from 45 BC
                </p>
              </div>
              <div className="aboutarea__list__2">
                <ul>
                  <li>
                    <i className="icofont-check" /> Explore a variety of fresh
                    educational teach
                  </li>
                  <li>
                    <i className="icofont-check" /> Explore a variety of fresh
                    educational teach
                  </li>
                  <li>
                    <i className="icofont-check" /> Explore a variety of fresh
                    educational teach
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
