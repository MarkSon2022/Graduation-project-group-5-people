import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import CourseList from "./CourseList";
import { useEffect } from "react";
import axios from "axios";
import { BASE_URL } from "../../data/const";
import { useHistory } from "react-router-dom";
import "./CourseDetail.css";
import { EventSidebar } from "./EventSidebar";
import Loading from "../Loading/Loading";
import moment from "moment";
import Comments from "../Comment/Comments";

export default function CourseDetails() {
  const [listCourse, setListCourse] = useState([]);
  const [courseDetail, setCourseDetail] = useState(null);
  const location = useLocation();
  const [activeModule, setActiveModule] = useState(0);
  const [isEnrolled, setIsEnrolled] = useState(false);
  useEffect(() => {
    axios.get(BASE_URL + "api/v1/courses/valid").then((response) => {
      setListCourse(response.data);
    });
  }, []);

  useEffect(() => {
    // Extract courseId from location search params
    const searchParams = new URLSearchParams(location.search);
    const courseId = searchParams.get("id");

    // Fetch course detail if courseId is available
    if (courseId) {
      fetchCourseDetail(courseId);
    }
  }, [location.search]);

  const fetchCourseDetail = async (courseId) => {
    try {
      const response = await axios.get(BASE_URL + `api/v1/courses/${courseId}`);
      const courseDetail = response.data;

      setCourseDetail(courseDetail); // Update courseDetail state
    } catch (error) {
      console.error("Error fetching course detail:", error);
    }
  };

  const handleCourseClick = async (event, courseId) => {
    event.preventDefault();
    fetchCourseDetail(courseId);
  };

  if (!courseDetail) {
    return <Loading />;
  }

  const skills = courseDetail.project.skill.split(",");
  var moduleLessons = courseDetail.courseModules.map(
    (course) => course.moduleLessons
  );
  var flattenedLessons = moduleLessons.flat();

  var videoUrl = flattenedLessons.map((module) => module.videoUrl);

  var maxStudent = courseDetail.courseCoursePackages.map(
    (course) => course.maxStudent
  );

  return (
    <>
      <div className="breadcrumbarea">
        <div className="container">
          <div className="row">
            <div className="col-xl-12">
              <div className="breadcrumb__content__wraper" data-aos="fade-up">
                <div className="breadcrumb__title">
                  <h2 className="heading">Course-Details</h2>
                </div>
                <div className="breadcrumb__inner">
                  <ul>
                    <li>
                      <a href="index.html">Home</a>
                    </li>
                    <li>Course-Details</li>
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

      <div>
        <div classname="blogarea__2 sp_top_100 sp_bottom_100"></div>
        <div className="container">
          <div className="row">
            <div className="col-xl-8 col-lg-8">
              <div
                className="blogarae__img__2 course__details__img__2"
                data-aos="fade-up"
              >
                <iframe
                  loading="lazy"
                  src={`https://www.youtube-nocookie.com/embed/${
                    courseDetail.project.introVideoUrl.split("=")[1]
                  }`}
                  frameBorder="0"
                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                  allowFullScreen
                ></iframe>

                {/* <div className="registerarea__content course__details__video">
                  <div className="registerarea__video">
                    <div className="video__pop__btn">
                      <a
                        className="video-btn"
                        href="https://www.youtube.com/watch?v=vHdclsdkp28"
                      >
                        {" "}
                        <img loading="lazy" src="img/icon/video.png" alt="" />
                      </a>
                    </div>
                  </div>
                </div> */}
              </div>

              <div className="blog__details__content__wraper">
                <div className="course__button__wraper" data-aos="fade-up">
                  <div className="course__button">
                    {/* <a href="#">Featured</a>
                    <a className="course__2" href="#">
                      Ux Design
                    </a> */}
                  </div>
                  <div className="course__date">
                    <p>
                      Last Update:<span> {moment(courseDetail.modifiedDate).format("DD MMM YYYY")}</span>
                    </p>
                  </div>
                </div>
                <div className="course__details__heading" data-aos="fade-up">
                  <h3>{courseDetail.name}</h3>
                </div>
                <div className="course__details__price" data-aos="fade-up">
                  <ul>
                    <li>
                      <div className="course__details__date">
                        <i className="icofont-book-alt" />{" "}
                        {courseDetail.duration} hour
                      </div>
                    </li>
                    <li>
                      <div className="course__star">
                        <i className="icofont-star" />
                        <i className="icofont-star" />
                        <i className="icofont-star" />
                        <i className="icofont-star" />
                        <i className="icofont-star" />
                        <span>(44)</span>
                      </div>
                    </li>
                  </ul>
                </div>
                <div className="course__details__paragraph" data-aos="fade-up">
                  <h4 className="sidebar__title" data-aos="fade-up">
                    Description
                  </h4>
                  <p>{courseDetail.description}</p>
                </div>
                <h4 className="sidebar__title" data-aos="fade-up">
                  Course Details
                </h4>
                <div className="course__details__wraper" data-aos="fade-up">
                  <ul>
                    <li>
                      Lectures : <span> 120 sub</span>
                    </li>
                    <li>
                      Duration : <span> {courseDetail.duration}h</span>
                    </li>
                    <li>
                      Enrolled : <span> 2 students</span>
                    </li>
                  </ul>
                  <ul>
                    <li>
                      Course level : <span>{courseDetail.age}</span>
                    </li>
                    <li>
                      Language : <span>English</span>
                    </li>
                    {/* <li>
                      Course Status : <span>{courseDetail.status}</span>
                    </li> */}
                  </ul>
                </div>
                <div
                  className="course__details__tab__wrapper"
                  data-aos="fade-up"
                >
                  <div className="row">
                    <div className="col-xl-12">
                      <ul
                        className="nav  course__tap__wrap"
                        id="myTab"
                        role="tablist"
                      >
                        <li className="nav-item" role="presentation">
                          <button
                            className="single__tab__link active"
                            data-bs-toggle="tab"
                            data-bs-target="#projects__two"
                            type="button"
                          >
                            <i className="icofont-book-alt" />
                            Curriculum
                          </button>
                        </li>
                      </ul>
                    </div>
                  </div>

                  <div
                    className="tab-content tab__content__wrapper"
                    id="myTabContent"
                  >
                    <div
                      className="tab-pane fade  active show"
                      id="projects__two"
                      role="tabpanel"
                      aria-labelledby="projects__two"
                    >
                      <div
                        className="accordion content__cirriculum__wrap"
                        id="accordionExample"
                      >
                        {courseDetail.courseModules.map((module, index) => (
                          <div className="accordion-item" key={module.id}>
                            <h2
                              className="accordion-header"
                              id={`heading${index}`}
                            >
                              <button
                                className="accordion-button"
                                type="button"
                                data-bs-toggle="collapse"
                                data-bs-target={`#collapse${index}`}
                                aria-expanded="true"
                                aria-controls={`#collapse${index}`}
                                onClick={() => setActiveModule(index)}
                              >
                                {module.title}
                                <span>{courseDetail.duration}hr</span>
                              </button>
                            </h2>
                            <div
                              id="collapseOne"
                              className={`accordion-collapse collapse ${
                                activeModule === index ? "show" : ""
                              }`}
                              aria-labelledby={`heading${index}`}
                              data-bs-parent="#accordionExample"
                            >
                              <div className="accordion-body">
                                {module.moduleLessons.map((lesson) => (
                                  <div className="scc__wrap" key={lesson.id}>
                                    <div className="scc__info ">
                                      <i className="icofont-video-alt" />
                                      <h5>
                                        <span>Video :</span> {lesson.title}
                                      </h5>
                                    </div>
                                    <div className="scc__meta">
                                      {/* <span className="time">
                                            <i className="icofont-clock-time" />{" "}
                                            {lesson.duration}
                                            minutes
                                          </span> */}
                                      <a href={videoUrl} target="_blank">
                                        {lesson.lock ? (
                                          <span>
                                            <i className="icofont-lock" />
                                          </span>
                                        ) : (
                                          <span
                                            className="question"
                                            style={{ marginBottom: "10px" }}
                                          >
                                            <i className="icofont-eye" />{" "}
                                            Preview
                                          </span>
                                        )}
                                      </a>
                                    </div>
                                  </div>
                                ))}
                              </div>
                            </div>
                          </div>
                        ))}
                      </div>
                    </div>
                  </div>
                </div>
                <div className="course__list__wraper" data-aos="fade-up">
                  <div className="blog__details__heading__2">
                    <h5>What Is Our Values?</h5>
                  </div>
                  <div
                    className="aboutarea__list__2 blog__details__list__2"
                    data-aos="fade-up"
                  >
                    <ul>
                      <li>
                        <i className="icofont-check" />
                        <p>
                          {courseDetail.income}
                        </p>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="course__list__wraper" data-aos="fade-up">
                  <div className="blog__details__heading__2">
                    <h5>Why Is Our Expectation?</h5>
                  </div>
                  <div
                    className="aboutarea__list__2 blog__details__list__2"
                    data-aos="fade-up"
                  >
                    <ul>
                      <li>
                        <i className="icofont-check" />
                        <p>
                          {courseDetail.outcome}
                        </p>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="blog__details__tag" data-aos="fade-up">
                  <ul>
                    <li style={{marginRight: '15px'}} className="heading__tag">Tag</li>
                    {skills.map((item, index) => (
                      <li key={index}>
                        <a href="#">{item.trim()}</a>
                      </li>
                    ))}
                  </ul>

                  <ul className="share__list">
                    <li className="heading__tag">Share</li>
                    <li>
                      <a href="#">
                        <i className="icofont-twitter" />
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        <i className="icofont-facebook" />
                      </a>
                    </li>
                    <li>
                      <a href="#">
                        <i className="icofont-instagram" />
                      </a>
                    </li>
                  </ul>
                </div>
                <Comments course={courseDetail} />
                {/* <div className="online__course__wrap">
                  <div className="instructor__heading__2" data-aos="fade-up">
                    <h3>Author More Courses</h3>
                    <a href="course.html">More Courses...</a>
                  </div>
                  <div
                    className="row instructor__slider__active row__custom__class"
                    data-aos="fade-up"
                  >
                    {listCourse.map((course) => (
                      <div
                        className="col-xl-6 column__custom__class"
                        key={course.id}
                      >
                        <div className="gridarea__wraper">
                          <div className="gridarea__img">
                            <a
                              href="#"
                              onClick={(event) =>
                                handleCourseClick(event, course.id)
                              }
                            >
                              <img
                                loading="lazy"
                                src={course.imgUrl}
                                alt="grid"
                              />
                            </a>
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
                                  <i className="icofont-clock-time" />{" "}
                                  {course.duration} hr
                                </li>
                              </ul>
                            </div>
                            <div className="gridarea__heading">
                              <h3>
                                <a href="course-details.html">{course.name}</a>
                              </h3>
                            </div>
                            <div className="gridarea__price">
                              {course.courseCoursePackages.price}
                              Enrolled
                              <span>
                                <del className="del__2">Unenroll</del>
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
                    ))}
                  </div>
                </div> */}
                {/* <div className="blog__details__comment">
                  <div
                    className="blog__details__comment__heading"
                    data-aos="fade-up"
                  >
                    <h5>(04) Comment</h5>
                  </div>
                  <div
                    className="blog__details__comment__inner"
                    data-aos="fade-up"
                  >
                    <div className="author__img">
                      <img
                        loading="lazy"
                        src="img/blog-details/blog-details__1.png"
                        alt="author"
                      />
                    </div>
                    <div className="author__content">
                      <div className="author__name">
                        <h6>
                          <a href="#">Rohan De Spond</a>
                        </h6>
                        <p>25 january 2023</p>
                      </div>
                      <div className="author__text">
                        <p>
                          There are many variations of passages of Lorem Ipsum
                          available, but the majority have. There are many
                          variations of passages of Lorem Ipsum available, but
                          the majority have
                        </p>
                      </div>
                    </div>
                    <div className="author__icon">
                      <button>
                        <svg
                          width={26}
                          height={19}
                          viewBox="0 0 26 19"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M5.91943 10.2031L12.1694 16.4531C13.3413 17.625 15.3726 16.8047 15.3726 15.125V12.3516C19.9819 12.5469 20.0991 13.5625 19.4351 15.8672C18.9272 17.5469 20.8413 18.9141 22.2866 17.9375C24.2788 16.5703 25.3726 14.8516 25.3726 12.3516C25.3726 6.76562 20.3726 5.67188 15.3726 5.47656V2.66406C15.3726 0.984375 13.3413 0.164062 12.1694 1.33594L5.91943 7.58594C5.17725 8.28906 5.17725 9.5 5.91943 10.2031ZM7.24756 8.875L13.4976 2.625V7.3125C18.1851 7.3125 23.4976 7.58594 23.4976 12.3516C23.4976 14.5391 22.3647 15.6328 21.2319 16.375C22.8335 11.0625 18.8491 10.4375 13.4976 10.4375V15.125L7.24756 8.875ZM0.919434 7.58594C0.177246 8.28906 0.177246 9.5 0.919434 10.2031L7.16943 16.4531C7.95068 17.2734 9.12256 17.1562 9.82568 16.4531L2.24756 8.875L9.82568 1.33594C9.12256 0.632812 7.95068 0.515625 7.16943 1.33594L0.919434 7.58594Z"
                            fill="#121416"
                          />
                        </svg>
                      </button>
                    </div>
                  </div>
                  <div
                    className="blog__details__comment__inner author__padding__left"
                    data-aos="fade-up"
                  >
                    <div className="author__img">
                      <img
                        loading="lazy"
                        src="img/blog-details/blog-details__2.png"
                        alt="author"
                      />
                    </div>
                    <div className="author__content">
                      <div className="author__name">
                        <h6>
                          <a href="#">Rohan De Spond</a>
                        </h6>
                        <p>25 january 2023</p>
                      </div>
                      <div className="author__text">
                        <p>
                          There are many variations of passages of Lorem Ipsum
                          available, but the majority have. There are many
                          variations of passages of Lorem Ipsum available, but
                          the majority have
                        </p>
                      </div>
                    </div>
                    <div className="author__icon">
                      <button>
                        <svg
                          width={26}
                          height={19}
                          viewBox="0 0 26 19"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M5.91943 10.2031L12.1694 16.4531C13.3413 17.625 15.3726 16.8047 15.3726 15.125V12.3516C19.9819 12.5469 20.0991 13.5625 19.4351 15.8672C18.9272 17.5469 20.8413 18.9141 22.2866 17.9375C24.2788 16.5703 25.3726 14.8516 25.3726 12.3516C25.3726 6.76562 20.3726 5.67188 15.3726 5.47656V2.66406C15.3726 0.984375 13.3413 0.164062 12.1694 1.33594L5.91943 7.58594C5.17725 8.28906 5.17725 9.5 5.91943 10.2031ZM7.24756 8.875L13.4976 2.625V7.3125C18.1851 7.3125 23.4976 7.58594 23.4976 12.3516C23.4976 14.5391 22.3647 15.6328 21.2319 16.375C22.8335 11.0625 18.8491 10.4375 13.4976 10.4375V15.125L7.24756 8.875ZM0.919434 7.58594C0.177246 8.28906 0.177246 9.5 0.919434 10.2031L7.16943 16.4531C7.95068 17.2734 9.12256 17.1562 9.82568 16.4531L2.24756 8.875L9.82568 1.33594C9.12256 0.632812 7.95068 0.515625 7.16943 1.33594L0.919434 7.58594Z"
                            fill="#121416"
                          />
                        </svg>
                      </button>
                    </div>
                  </div>
                  <div
                    className="blog__details__comment__inner"
                    data-aos="fade-up"
                  >
                    <div className="author__img">
                      <img
                        loading="lazy"
                        src="img/blog-details/blog-details__3.png"
                        alt="author"
                      />
                    </div>
                    <div className="author__content">
                      <div className="author__name">
                        <h6>
                          <a href="#">Rohan De Spond</a>
                        </h6>
                        <p>25 january 2023</p>
                      </div>
                      <div className="author__text">
                        <p>
                          There are many variations of passages of Lorem Ipsum
                          available, but the majority have. There are many
                          variations of passages of Lorem Ipsum available, but
                          the majority have
                        </p>
                      </div>
                    </div>
                    <div className="author__icon">
                      <button>
                        <svg
                          width={26}
                          height={19}
                          viewBox="0 0 26 19"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M5.91943 10.2031L12.1694 16.4531C13.3413 17.625 15.3726 16.8047 15.3726 15.125V12.3516C19.9819 12.5469 20.0991 13.5625 19.4351 15.8672C18.9272 17.5469 20.8413 18.9141 22.2866 17.9375C24.2788 16.5703 25.3726 14.8516 25.3726 12.3516C25.3726 6.76562 20.3726 5.67188 15.3726 5.47656V2.66406C15.3726 0.984375 13.3413 0.164062 12.1694 1.33594L5.91943 7.58594C5.17725 8.28906 5.17725 9.5 5.91943 10.2031ZM7.24756 8.875L13.4976 2.625V7.3125C18.1851 7.3125 23.4976 7.58594 23.4976 12.3516C23.4976 14.5391 22.3647 15.6328 21.2319 16.375C22.8335 11.0625 18.8491 10.4375 13.4976 10.4375V15.125L7.24756 8.875ZM0.919434 7.58594C0.177246 8.28906 0.177246 9.5 0.919434 10.2031L7.16943 16.4531C7.95068 17.2734 9.12256 17.1562 9.82568 16.4531L2.24756 8.875L9.82568 1.33594C9.12256 0.632812 7.95068 0.515625 7.16943 1.33594L0.919434 7.58594Z"
                            fill="#121416"
                          />
                        </svg>
                      </button>
                    </div>
                  </div>
                  <div
                    className="blog__details__comment__inner author__padding__left"
                    data-aos="fade-up"
                  >
                    <div className="author__img">
                      <img
                        loading="lazy"
                        src="img/blog-details/blog-details__4.png"
                        alt="author"
                      />
                    </div>
                    <div className="author__content">
                      <div className="author__name">
                        <h6>
                          <a href="#">Rohan De Spond</a>
                        </h6>
                        <p>25 january 2023</p>
                      </div>
                      <div className="author__text">
                        <p>
                          There are many variations of passages of Lorem Ipsum
                          available, but the majority have. There are many
                          variations of passages of Lorem Ipsum available, but
                          the majority have
                        </p>
                      </div>
                    </div>
                    <div className="author__icon">
                      <button>
                        <svg
                          width={26}
                          height={19}
                          viewBox="0 0 26 19"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M5.91943 10.2031L12.1694 16.4531C13.3413 17.625 15.3726 16.8047 15.3726 15.125V12.3516C19.9819 12.5469 20.0991 13.5625 19.4351 15.8672C18.9272 17.5469 20.8413 18.9141 22.2866 17.9375C24.2788 16.5703 25.3726 14.8516 25.3726 12.3516C25.3726 6.76562 20.3726 5.67188 15.3726 5.47656V2.66406C15.3726 0.984375 13.3413 0.164062 12.1694 1.33594L5.91943 7.58594C5.17725 8.28906 5.17725 9.5 5.91943 10.2031ZM7.24756 8.875L13.4976 2.625V7.3125C18.1851 7.3125 23.4976 7.58594 23.4976 12.3516C23.4976 14.5391 22.3647 15.6328 21.2319 16.375C22.8335 11.0625 18.8491 10.4375 13.4976 10.4375V15.125L7.24756 8.875ZM0.919434 7.58594C0.177246 8.28906 0.177246 9.5 0.919434 10.2031L7.16943 16.4531C7.95068 17.2734 9.12256 17.1562 9.82568 16.4531L2.24756 8.875L9.82568 1.33594C9.12256 0.632812 7.95068 0.515625 7.16943 1.33594L0.919434 7.58594Z"
                            fill="#121416"
                          />
                        </svg>
                      </button>
                    </div>
                  </div>
                </div> */}
              </div>
            </div>
            <div className="col-xl-4 col-lg-4">
              <div className="course__details__sidebar">
                <EventSidebar courseDetail={courseDetail} />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
