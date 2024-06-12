import React, { useState } from "react";
import { useSelector } from "react-redux";
import { NavLink } from "react-router-dom/cjs/react-router-dom";
 const GridArea = () => {
  
  const handleTrendingCourse = (category) =>{


  }

    
  
  return (
    <div className="gridarea gridarea__2">
      <div className="container">
        <div className="row grid__row">
          <div
            className="col-xl-5 col-lg-5 col-md-12 col-sm-12"
            data-aos="fade-up"
          >
            <div className="section__title__2">
              <div className="section__title__button">
                <div className="default__small__button">Course List</div>
              </div>
              <div className="section__title__heading__2 section__title__heading__3 heading__fontsize__2">
                <h2>Perfect Online Course Your Carrer</h2>
              </div>
            </div>
          </div>
          <div
            className="col-xl-7 col-lg-7 col-md-12 col-sm-12"
            data-aos="fade-up"
          >
            <div className="gridfilter_nav grid__filter__2 gridFilter">
              <button className="active" data-filter="*">
                See All
              </button>
              <button data-filter=".filter1" className>
                Data science{" "}
              </button>
              <button data-filter=".filter2" className>
                Engineering{" "}
              </button>
              <button data-filter=".filter3" className>
                Featured{" "}
              </button>
              <button data-filter=".filter4" className>
                Architecture
              </button>
            </div>
          </div>
        </div>
        <div className="row grid" data-aos="fade-up">
        
          <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 grid-item filter1 filter3">
            <div className="gridarea__wraper">
              <div className="gridarea__img">
                <NavLink to="/course-detail">
                  <img loading="lazy" src="img/grid/grid_1.png" alt="grid" />
                </NavLink>
                <div className="gridarea__small__button">
                  <div className="grid__badge">Data &amp; Tech</div>
                </div>
                <div className="gridarea__small__icon">
                  <a href="#">
                    <i className="icofont-heart-alt" />
                  </a>
                </div>
              </div>
              <div className="gridarea__content">
                <div className="gridarea__list">
                  <ul>
                    <li>
                      <i className="icofont-book-alt" /> 23 Lesson
                    </li>
                    <li>
                      <i className="icofont-clock-time" /> 1 hr 30 min
                    </li>
                  </ul>
                </div>
                <div className="gridarea__heading">
                  <h3>
                    <NavLink to="/course-detail">
                      Foundation course to under stand about softwere
                    </NavLink>
                  </h3>
                </div>
                <div className="gridarea__price">
                  $32.00 <del>/ $67.00</del>
                  <span>
                    {" "}
                    <del className="del__2">Free</del>
                  </span>
                </div>
                <div className="gridarea__bottom">
                  <a href="instructor-details.html">
                    <div className="gridarea__small__img">
                      <img
                        loading="lazy"
                        src="img/grid/grid_small_1.jpg"
                        alt="grid"
                      />
                      <div className="gridarea__small__content">
                        <h6>Micle Jhon</h6>
                      </div>
                    </div>
                  </a>
                  <div className="gridarea__star">
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <span>(44)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 grid-item filter2 filter3">
            <div className="gridarea__wraper">
              <div className="gridarea__img">
                <img loading="lazy" src="img/grid/grid_2.png" alt="grid" />
                <div className="gridarea__small__button">
                  <div className="grid__badge blue__color">Mechanical</div>
                </div>
                <div className="gridarea__small__icon">
                  <a href="#">
                    <i className="icofont-heart-alt" />
                  </a>
                </div>
              </div>
              <div className="gridarea__content">
                <div className="gridarea__list">
                  <ul>
                    <li>
                      <i className="icofont-book-alt" /> 29 Lesson
                    </li>
                    <li>
                      <i className="icofont-clock-time" /> 2 hr 10 min
                    </li>
                  </ul>
                </div>
                <div className="gridarea__heading">
                  <h3>
                    <a href="#">Nidnies course to under stand about softwere</a>
                  </h3>
                </div>
                <div className="gridarea__price green__color">
                  $32.00<del>/$67.00</del>
                  <span>.Free</span>
                </div>
                <div className="gridarea__bottom">
                  <a href="instructor-details.html">
                    <div className="gridarea__small__img">
                      <img
                        loading="lazy"
                        src="img/grid/grid_small_2.jpg"
                        alt="grid"
                      />
                      <div className="gridarea__small__content">
                        <h6>Rinis Jhon</h6>
                      </div>
                    </div>
                  </a>
                  <div className="gridarea__star">
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <span>(44)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 grid-item filter4 filter5">
            <div className="gridarea__wraper">
              <div className="gridarea__img">
                <a href="course-details.html">
                  <img loading="lazy" src="img/grid/grid_3.png" alt="grid" />
                </a>
                <div className="gridarea__small__button">
                  <div className="grid__badge pink__color">Development</div>
                </div>
                <div className="gridarea__small__icon">
                  <a href="#">
                    <i className="icofont-heart-alt" />
                  </a>
                </div>
              </div>
              <div className="gridarea__content">
                <div className="gridarea__list">
                  <ul>
                    <li>
                      <i className="icofont-book-alt" /> 25 Lesson
                    </li>
                    <li>
                      <i className="icofont-clock-time" /> 1 hr 40 min
                    </li>
                  </ul>
                </div>
                <div className="gridarea__heading">
                  <h3>
                    <a href="course-details.html">
                      Minws course to under stand about solution
                    </a>
                  </h3>
                </div>
                <div className="gridarea__price">
                  $40.00 <del>/ $67.00</del>
                  <span>
                    {" "}
                    <del className="del__2">Free</del>
                  </span>
                </div>
                <div className="gridarea__bottom">
                  <a href="instructor-details.html">
                    <div className="gridarea__small__img">
                      <img
                        loading="lazy"
                        src="img/grid/grid_small_3.jpg"
                        alt="grid"
                      />
                      <div className="gridarea__small__content">
                        <h6>Micle Jhon</h6>
                      </div>
                    </div>
                  </a>
                  <div className="gridarea__star">
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <span>(44)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 grid-item filter4">
            <div className="gridarea__wraper">
              <div className="gridarea__img">
                <a href="course-details.html">
                  <img loading="lazy" src="img/grid/grid_4.png" alt="grid" />
                </a>
                <div className="gridarea__small__button">
                  <div className="grid__badge green__color">
                    Ui &amp; UX Design
                  </div>
                </div>
                <div className="gridarea__small__icon">
                  <a href="#">
                    <i className="icofont-heart-alt" />
                  </a>
                </div>
              </div>
              <div className="gridarea__content">
                <div className="gridarea__list">
                  <ul>
                    <li>
                      <i className="icofont-book-alt" /> 36 Lesson
                    </li>
                    <li>
                      <i className="icofont-clock-time" /> 3 hr 40 min
                    </li>
                  </ul>
                </div>
                <div className="gridarea__heading">
                  <h3>
                    <a href="course-details.html">
                      Design course to under stand about solution
                    </a>
                  </h3>
                </div>
                <div className="gridarea__price">
                  $40.00 <del>/ $67.00</del>
                  <span>
                    {" "}
                    <del className="del__2">Free</del>
                  </span>
                </div>
                <div className="gridarea__bottom">
                  <a href="instructor-details.html">
                    <div className="gridarea__small__img">
                      <img
                        loading="lazy"
                        src="img/grid/grid_small_4.jpg"
                        alt="grid"
                      />
                      <div className="gridarea__small__content">
                        <h6>Micle Robin</h6>
                      </div>
                    </div>
                  </a>
                  <div className="gridarea__star">
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <span>(44)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 grid-item filter1 filter3">
            <div className="gridarea__wraper">
              <div className="gridarea__img">
                <a href="course-details.html">
                  <img loading="lazy" src="img/grid/grid_5.png" alt="grid" />
                </a>
                <div className="gridarea__small__button">
                  <div className="grid__badge orange__color">
                    Data &amp; Tech
                  </div>
                </div>
                <div className="gridarea__small__icon">
                  <a href="#">
                    <i className="icofont-heart-alt" />
                  </a>
                </div>
              </div>
              <div className="gridarea__content">
                <div className="gridarea__list">
                  <ul>
                    <li>
                      <i className="icofont-book-alt" /> 36 Lesson
                    </li>
                    <li>
                      <i className="icofont-clock-time" /> 3 hr 40 min
                    </li>
                  </ul>
                </div>
                <div className="gridarea__heading">
                  <h3>
                    <a href="course-details.html">
                      Data course to under stand about solution
                    </a>
                  </h3>
                </div>
                <div className="gridarea__price">
                  $40.00 <del>/ $67.00</del>
                  <span>
                    {" "}
                    <del className="del__2">Free</del>
                  </span>
                </div>
                <div className="gridarea__bottom">
                  <a href="instructor-details.html">
                    <div className="gridarea__small__img">
                      <img
                        loading="lazy"
                        src="img/grid/grid_small_5.jpg"
                        alt="grid"
                      />
                      <div className="gridarea__small__content">
                        <h6>Micle Robin</h6>
                      </div>
                    </div>
                  </a>
                  <div className="gridarea__star">
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <span>(44)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12 grid-item filter5 filter2">
            <div className="gridarea__wraper">
              <div className="gridarea__img">
                <a href="course-details.html">
                  <img loading="lazy" src="img/grid/grid_6.png" alt="grid" />
                </a>
                <div className="gridarea__small__button">
                  <div className="grid__badge yellow__color">Big Data</div>
                </div>
                <div className="gridarea__small__icon">
                  <a href="#">
                    <i className="icofont-heart-alt" />
                  </a>
                </div>
              </div>
              <div className="gridarea__content">
                <div className="gridarea__list">
                  <ul>
                    <li>
                      <i className="icofont-book-alt" /> 30 Lesson
                    </li>
                    <li>
                      <i className="icofont-clock-time" /> 3 hr 40 min
                    </li>
                  </ul>
                </div>
                <div className="gridarea__heading">
                  <h3>
                    <a href="course-details.html">
                      Big data to under stand about solution pacage
                    </a>
                  </h3>
                </div>
                <div className="gridarea__price">
                  $40.00 <del>/ $67.00</del>
                  <span>
                    {" "}
                    <del className="del__2">Free</del>
                  </span>
                </div>
                <div className="gridarea__bottom">
                  <a href="instructor-details.html">
                    <div className="gridarea__small__img">
                      <img
                        loading="lazy"
                        src="img/grid/grid_small_5.jpg"
                        alt="grid"
                      />
                      <div className="gridarea__small__content">
                        <h6>Micle Robin</h6>
                      </div>
                    </div>
                  </a>
                  <div className="gridarea__star">
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <i className="icofont-star" />
                    <span>(44)</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GridArea;
