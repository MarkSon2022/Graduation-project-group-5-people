import React, { useEffect, useState } from "react";
import ShortByNew from "../../components/Course/ShortByNew";
import Sidebar from "../../components/Course/Sidebar";
import CourseList from "../../components/Course/CourseList";
import axios from "axios";
import { BASE_URL } from "../../data/const";
import Loading from "../../components/Loading/Loading";

export default function Course() {
  const [data, setData] = useState(null);
  const [dataFiltered, setDataFiltered] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  useEffect(() => {
    axios.get(BASE_URL + "api/v1/courses/valid").then((response) => {
      setData(response.data);
      setDataFiltered(response.data);
      setIsLoading(false);
    });
  }, []);
  if (isLoading) {
    return <Loading />;
  }
  return (
    <>
      {data && dataFiltered ? (
        <>
          <div className="breadcrumbarea">
            <div className="container">
              <div className="row">
                <div className="col-xl-12">
                  <div
                    className="breadcrumb__content__wraper"
                    data-aos="fade-up"
                  >
                    <div className="breadcrumb__title">
                      <h2 className="heading">All Our Courses</h2>
                    </div>
                    <div className="breadcrumb__inner">
                      <ul>
                        {/* <li>
                          <a href="index.html">Home</a>
                        </li> */}
                        <li> Helpful Courses From GCP Edu</li>
                      </ul>
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

          <div class="coursearea sp_top_100 sp_bottom_100">
            <div class="container">
              <div class="row">
                <ShortByNew data={data}/>

                <Sidebar data={data} setDataFiltered={setDataFiltered} />

                <CourseList data={dataFiltered} />
              </div>
            </div>
          </div>
        </>
      ) : (
        <></>
      )}
    </>
  );
}
