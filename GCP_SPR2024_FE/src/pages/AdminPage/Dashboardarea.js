import React from 'react'

export default function Dashboardarea() {
  return (
    <div>  <div className="">
    <div className="row">
      <div className="col-xl-12">
        <div className="dashboardarea__wraper">
          <div className="dashboardarea__img">
            <div className="dashboardarea__inner admin__dashboard__inner">
              <div className="dashboardarea__left">
                <div className="dashboardarea__left__img">
                  <img
                    loading="lazy"
                    src="../img/dashbord/dashbord__2.jpg"
                    alt
                  />
                </div>
                <div className="dashboardarea__left__content">
                  <h5>Hello</h5>
                  <h4>Michle Obema</h4>
                </div>
              </div>
              
              <div className="dashboardarea__right">
                <div className="dashboardarea__right__button">
                  <a className="default__button" href="create-course.html">
                    Create a New Course
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width={24}
                      height={24}
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      strokeWidth={2}
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      className="feather feather-arrow-right"
                    >
                      <line x1={5} y1={12} x2={19} y2={12} />
                      <polyline points="12 5 19 12 12 19" />
                    </svg>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div></div>
  )
}
