import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import { BASE_URL } from "../../data/const";
import CourseDetails from "./CourseDetails";
import "./EventSidebar.css";
export const EventSidebar = ({ courseDetail }) => {
  const history = useHistory();
  const [isEnrolled, setIsEnrolled] = useState(false);
  const [user, setUser] = useState(null);
  const [canEnroll, setCanEnroll] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  useEffect(() => {
    const storedUser = localStorage.getItem("USER");
    console.log(storedUser);
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

  useEffect(() => {
    const fetchLearningInfo = async () => {
      try {
        setIsLoading(true);
        const courseValid = {
          courseId: courseDetail.id,
          learnerId: user.userId,
        };
        
        axios.post(
          BASE_URL + `api/v1/group/enroll`, courseValid
        ).then((response) => {
          console.log(response.data);
          if (response.status == 200 || response.status == 201) {
            console.log(response.data)
            setCanEnroll(true);
          }
          else {
            setCanEnroll(false);
          }
        }).catch((error) => {
          setCanEnroll(false);
        });

      } catch (error) {
        console.error("Error fetching learning info:", error);
        setCanEnroll(false);
      } finally {
        setIsLoading(false); // Reset loading state
      }
    };

    if (user) {
      fetchLearningInfo();
    }
  }, [user]);

  useEffect(() => {
    const fetchEnrollments = async () => {
      if (user && user.role === "LEARNER") {
        try {
          const response = await axios.get(
            BASE_URL + `api/v1/enrollments/learner/${user.userId}`
          );
          const enrollments = response.data;

          if (
            enrollments.some(
              (enrollment) => enrollment.course.id === courseDetail.id
            )
          ) {
            setIsEnrolled(true);
          }
        } catch (error) {
          console.error("Error fetching enrollments:", error);
        }
      }
    };

    fetchEnrollments();
  }, [user, courseDetail]);

  const handleBuySubscription = () => {
    history.push("/subscription", { courseDetail });
  };

  const handleEnroll = async () => {
    try {
      setIsLoading(true);
      const response = await axios.post(
        BASE_URL + "api/v1/enrollments",
        {
          courseId: courseDetail.id,
          learnerId: user.userId,
        },
        {
          headers: {
            Accept: "application/json",
          },
          validateStatus: function (status) {
            return (status >= 200 && status < 300) || status === 406; // default
          },
        }
      );

      if (response.status === 200 || response.status === 406) {
        setIsEnrolled(true);
        history.push("/lesson-detail", { courseDetail });
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="event__sidebar__wraper" data-aos="fade-up">
      <div
        className="blogarae__img__2 course__details__img__2"
        data-aos="fade-up"
      >
        <img
          className="myImage"
          loading="lazy"
          src={courseDetail.imgUrl}
          alt="Course detail"
        />
      </div>

      <div className="event__price__wraper"></div>
      <div className="course__summery__button">
        {isLoading ? (
          <div className="dot_loading">
            <span>.</span>
            <span>.</span>
            <span>.</span>
          </div>
        ) : (
          <>
            {!user ? (
              <button
                className="default__button"
                onClick={() => history.push("/sign-in")}
              >
                Login
              </button>
            ) : (
              <>
                {user.role === "LEARNER" &&
                  (!isEnrolled ? (
                    <button
                      className="default__button"
                      onClick={handleEnroll}
                      disabled={!canEnroll}
                    >
                      Enroll
                    </button>
                  ) : (
                    <button
                      className="default__button default__button--2 "
                      onClick={() =>
                        history.push("/lesson-detail", { courseDetail })
                      }
                      // disabled={!canEnroll}
                    >
                      Continue
                    </button>
                  ))}
                {user.role === "ORGANIZATION" && (
                  <a
                    type="button"
                    className="default__button default__button--1 "
                    href=""
                    onClick={handleBuySubscription}
                  >
                    Buy Subscription
                  </a>
                )}
              </>
            )}
          </>
        )}
      </div>

      <div className="course__summery__lists">
        <ul>
          {/* <li>
            <div className="course__summery__item">
              <span className="sb_label">Start Date</span>
              <span className="sb_content">05 Dec 2024</span>
            </div>
          </li> */}
          <li>
            <div className="course__summery__item">
              <span className="sb_label">Total Duration</span>
              <span className="sb_content">{courseDetail.duration} hr</span>
            </div>
          </li>
          <li>
            <div className="course__summery__item">
              <span className="sb_label">Enrolled</span>
              <span className="sb_content">100</span>
            </div>
          </li>
          <li>
            <div className="course__summery__item">
              <span className="sb_label">Skill Level</span>
              <span className="sb_content">{courseDetail.age}</span>
            </div>
          </li>
          <li>
            <div className="course__summery__item">
              <span className="sb_label">Language</span>
              <span className="sb_content">English</span>
            </div>
          </li>
          <li>
            <div className="course__summery__item">
              <span className="sb_label">Quiz</span>
              <span className="sb_content">Yes</span>
            </div>
          </li>
          <li>
            <div className="course__summery__item">
              <span className="sb_label">Certificate</span>
              <span className="sb_content">Yes</span>
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
};
