import React from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";
import { BASE_URL } from "../../data/const";

export default function CourseList({ data }) {
  const history = useHistory();

  const handleCourseClick = async (event, courseId) => {
    event.preventDefault();
    try {
      const response = await axios.get(BASE_URL + `api/v1/courses/${courseId}`);
      const courseDetail = response.data;
      // Navigate to the course detail page with course detail data
      history.push(`/course-detail?id=${courseId}`, { courseDetail });
      console.log(history);
    } catch (error) {
      console.error("Error fetching course detail:", error);
    }
  };

  return (
    <>
      <div className="col-xl-9 col-lg-9 col-md-8 col-12">
        <div
          className="tab-content tab__content__wrapper with__sidebar__content"
          id="myTabContent"
        >
          <div
            className="tab-pane fade  active show"
            id="projects__one"
            role="tabpanel"
            aria-labelledby="projects__one"
          >
            <div className="row">
              {data.map((course, index) => (
                <div
                  className="col-xl-4 col-lg-6 col-md-12 col-sm-6 col-12"
                  data-aos="fade-up"
                  key={index}
                >
                  <div className="gridarea__wraper gridarea__wraper__2">
                    <div className="gridarea__img">
                      <a
                        href="#"
                        onClick={(event) => handleCourseClick(event, course.id)}
                      >
                        <img style={{height: '150px'}} loading="lazy" src={course.imgUrl} alt="grid" />
                      </a>
                      <div className="gridarea__small__button">
                        <div className="grid__badge">
                          {course.project.skill}
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
                            <i className="icofont-book-alt" />{" "}
                            {course.courseModules[0]?.moduleLessons?.length}{" "}
                            Lesson
                          </li>
                          <li>
                            <i className="icofont-clock-time" />{" "}
                            {course.duration} min
                          </li>
                        </ul>
                      </div>
                      <div className="gridarea__heading">
                        <h3>
                          <a href={`/course-detail?id=${course.id}`}>
                            {course.name}
                          </a>
                        </h3>
                      </div>
                      <div className="gridarea__price">
                        {course.courseCoursePackages[0]?.price} VND <span> </span>
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
                              <h6>Mirnsdo .H</h6>
                            </div>
                          </div>
                        </a>
                        <div className="gridarea__star">
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
              ))}
            </div>
          </div>
        </div>
        <div className="main__pagination__wrapper" data-aos="fade-up">
          <ul className="main__page__pagination">
            <li>
              <a className="disable" href="#">
                <i className="icofont-double-left" />
              </a>
            </li>
            <li>
              <a className="active" href="#">
                1
              </a>
            </li>
            <li>
              <a href="#">2</a>
            </li>
            <li>
              <a href="#">3</a>
            </li>
            <li>
              <a href="#">
                <i className="icofont-double-right" />
              </a>
            </li>
          </ul>
        </div>
      </div>
    </>
  );
}
