import React from "react";

export default function TopBar() {
  return (
    <div className="topbararea topbararea--2">
      <div className="container">
        <div className="row">
          <div className="col-xl-6 col-lg-6">
            <div className="topbar__left">
              <ul>
                <li>Call Us: (+84 ) 123-456-789 </li>
                <li> - Mail Us: Course@mail.com</li>
              </ul>
            </div>
          </div>
          <div className="col-xl-6 col-lg-6">
            <div className="topbar__right">
              <div className="topbar__icon">
                <i className="icofont-location-pin" />
              </div>
              <div className="topbar__text">
                <p>FPT University Thu Duc city, Viet Nam</p>
              </div>
              <div className="topbar__list">
                <ul>
                  <li>
                    <a href="#">
                      <i className="icofont-facebook" />
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="icofont-twitter" />
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="icofont-instagram" />
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="icofont-youtube-play" />
                    </a>
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
