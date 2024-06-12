import React from "react";

export default function ShortByNew({data}) {
  return (
    <>
      <div className="col-xl-12">
        <div className="course__text__wraper" data-aos="fade-up">
          <div className="course__text">
            <p>Showing {data != null ? data.length : 0} Course Results</p>
          </div>
          <div className="course__icon">
            <ul className="nav property__team__tap" id="myTab" role="tablist">
              <li className="nav-item" role="presentation">
                <a
                  href="#"
                  className="single__tab__link active"
                  data-bs-toggle="tab"
                  data-bs-target="#projects__one"
                >
                  <i className="icofont-layout" />
                </a>
              </li>
              <li className="nav-item" role="presentation">
                <a
                  href="#"
                  className="single__tab__link"
                  data-bs-toggle="tab"
                  data-bs-target="#projects__two"
                >
                  <i className="icofont-listine-dots" />
                </a>
              </li>
              <li className="short__by__new">
                <select
                  className="form-select"
                  aria-label="Default select example"
                >
                  <option selected>Short by New</option>
                  <option value={1}>One</option>
                  <option value={2}>Two</option>
                  <option value={3}>Three</option>
                </select>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </>
  );
}
