import React, { useEffect, useRef, useState } from "react";
import "./LessonDetail.css";
import { FaArrowLeft, FaRegFileAlt } from "react-icons/fa";
import { Link, useLocation } from "react-router-dom";
import { useHistory } from "react-router-dom";
import Quiz from "../QuizPage/Quiz";
import axios from "axios";
import { BASE_URL } from "../../data/const";
import Loading from "../../components/Loading/Loading";

const LessonDetail = () => {
  const history = useHistory();
  const location = useLocation();
  const [activeModule, setActiveModule] = useState(0);
  const [currentVideoUrl, setCurrentVideoUrl] = useState("");
  const [currentLessonTitle, setCurrentLessonTitle] = useState("");
  const [currentLesson, setCurrentLesson] = useState({});
  const [currentLessonIndex, setCurrentLessonIndex] = useState(0);
  const [showQuiz, setShowQuiz] = useState(false);
  const [quizData, setQuizData] = useState(null);
  const [quizLoading, setQuizLoading] = useState(false);
  const [lessonExistence, setLessonExistence] = useState({});
  const [isLoading, setIsLoading] = useState(true);
  const user = JSON.parse(localStorage.getItem("USER"));
  const [learningInfo, setLearningInfo] = useState({});
  const [learnerAnswers, setLearnerAnswers] = useState(null);

  async function fetchQuizData(id) {
    const user = localStorage.getItem("USER");
    axios.post(BASE_URL + "api/v1/learner-answer/check-valid", {quizId: id, learnerId: JSON.parse(user)?.userId})
    .then((response) => {
      if (response.status === 200) {
        if (response?.data?.isRetake == false) {
          setLearnerAnswers(response.data);
        }
        else {
          return;
        }
      }
      else {
        return;
      }
    })
    .catch((error) => {return;});

    setQuizLoading(true);
    const response = await axios(BASE_URL + `api/v1/quiz/detail/${id}`);
    const data = response.data;
    setQuizData(data);
    setQuizLoading(false);
    return data;
  }

  const courseDetail = location.state.courseDetail;
  // Assuming lessonExistence is an object with lesson IDs as keys and their existence status (true/false) as values
  const lessonIds = Object.keys(lessonExistence);
  const totalLessons = lessonIds.length;
  const learnedLessons = lessonIds.filter((id) => lessonExistence[id]).length;
  const learnedLessonIds = lessonIds.filter((id) => lessonExistence[id]);
  const numberOfLearnedLessons = learnedLessonIds.length;
  const percentage = Math.floor((learnedLessons / totalLessons) * 1000) / 10;
  const radius = 20;
  const circumference = 2 * Math.PI * radius;
  const offset = circumference - (percentage / 100) * circumference;

  useEffect(() => {
    // Check if there's a lesson in the current module
    const module = courseDetail.courseModules[activeModule];
    if (module && module.moduleLessons.length > 0) {
      const lesson = module.moduleLessons[0];
      const activeLessonIndex = module.moduleLessons.findIndex(
        (lesson) => lesson.title === currentLessonTitle
      );

      if (lesson) {
        setCurrentLesson(lesson);
        const videoId = getYouTubeID(lesson.videoUrl);
        setCurrentVideoUrl(
          `https://www.youtube-nocookie.com/embed/${videoId}?enablejsapi=1&origin=${window.location.origin}`
        );
        setCurrentLessonIndex(activeLessonIndex);
      }
    }
  }, [currentLessonTitle, location.state.courseDetail.courseModules]);

  const goToNextLesson = () => {
    setCurrentLessonIndex((prevIndex) => {
      const totalModules = courseDetail.courseModules.length;
      const totalLessons =
        courseDetail.courseModules[activeModule].moduleLessons.length;
      const nextIndex = prevIndex + 1;

      if (nextIndex < totalLessons) {
        const nextLesson =
          courseDetail.courseModules[activeModule].moduleLessons[nextIndex];
        setCurrentVideoUrl(getYouTubeID(nextLesson.videoUrl));
        setShowQuiz(false);
        return nextIndex;
      } else {
        const nextModule = activeModule + 1;
        if (nextModule < totalModules) {
          setActiveModule(nextModule);
          const nextLesson =
            courseDetail.courseModules[nextModule].moduleLessons[0];
          setCurrentVideoUrl(getYouTubeID(nextLesson.videoUrl));
          setShowQuiz(false);
          return 0;
        } else {
          return prevIndex;
        }
      }
    });
  };
  const goToPreviousLesson = () => {
    setCurrentLessonIndex((prevIndex) => {
      const prevModule = activeModule - 1;
      if (prevIndex > 0) {
        const prevLesson =
          courseDetail.courseModules[activeModule].moduleLessons[prevIndex - 1];
        setCurrentVideoUrl(getYouTubeID(prevLesson.videoUrl));
        setShowQuiz(false);
        return prevIndex - 1;
      } else if (prevModule >= 0) {
        setActiveModule(prevModule);
        const totalLessons =
          courseDetail.courseModules[prevModule].moduleLessons.length;
        const prevLesson =
          courseDetail.courseModules[prevModule].moduleLessons[
            totalLessons - 1
          ];
        setCurrentVideoUrl(getYouTubeID(prevLesson.videoUrl));
        setShowQuiz(false);
        return totalLessons - 1;
      } else {
        return prevIndex;
      }
    });
  };

  function getYouTubeID(url) {
    var ID = "";
    url = url
      .replace(/(>|<)/gi, "")
      .split(/(vi\/|v=|\/v\/|youtu\.be\/|\/embed\/)/);
    if (url[2] !== undefined) {
      ID = url[2].split(/[^0-9a-z_\-]/i);
      ID = ID[0];
    } else {
      ID = url;
    }
    return ID;
  }

  const handleQuizClick = async (id) => {
    const data = await fetchQuizData(id);
    setQuizData(data);
    setShowQuiz(true);
  };

  const checkLessonExistence = (lessonIds, learnedLessons) => {
    const lessonExistence = {};
    lessonIds.forEach((lessonId) => {
      const lessonIdString = lessonId.toString();
      const lessonExists = learnedLessons.includes(lessonIdString);
      lessonExistence[lessonIdString] = lessonExists;
    });
    return lessonExistence;
  };

  // Retrieve the user ID from local storage

  useEffect(() => {
    const fetchLessonExistence = async () => {
      try {
        const response = await axios.get(
          BASE_URL + `api/v1/learning-info/${user.userId}`
        );
        const responseData = response.data;
        setLearningInfo(responseData);
        const learnedLessons = responseData.learnedLesson
          .split(",")
          .map((id) => id.trim());

        // Assuming courseDetail is available in the component scope
        const lessonIdsToCheck = courseDetail.courseModules.flatMap((course) =>
          course.moduleLessons.map((lesson) => lesson.id)
        );

        const lessonExistence = checkLessonExistence(
          lessonIdsToCheck,
          learnedLessons
        );
        setLessonExistence(lessonExistence);
        setIsLoading(false);
      } catch (error) {
        console.error("Error fetching learned lessons:", error);
      }
    };

    fetchLessonExistence();
  }, []);

  const handleUpdateLearnedLesson = async (lessonId) => {
    try {
      // Prepare the request body

      const requestBody = {
        learnerId: user.userId,
        learnedLesson: lessonId,
        courseId: courseDetail.id
      };
      console.log(lessonId);
      // Make a POST request to update the learned lesson
      const response = await axios.post(
        `${BASE_URL}api/v1/learning-info/lesson`,
        requestBody
      );

      // Assuming that lessonExistence is updated after the API call
      const updatedLessonExistence = { ...lessonExistence, [lessonId]: true };
      setLessonExistence(updatedLessonExistence);
    } catch (error) {
      console.error("Error updating learned lesson:", error);
      // Handle errors as needed
    }
  };

  return (
    <div className="LessonContainer">
      <header>
        <div className="header-content">
          <button
            className="back-arrow"
            onClick={() => history.push(`/course-detail?id=${courseDetail.id}`)}
          >
            <FaArrowLeft />
          </button>
          <img src="img/logo/logo_1.png" alt="Logo" className="logo" />{" "}
          {/* Logo */}
          <div className="lesson-progress">
            <div className="percentage-circle">
              <svg width="44" height="44">
                <circle
                  stroke="white"
                  strokeWidth="2"
                  fill="transparent"
                  r={radius}
                  cx="22"
                  cy="22"
                />
                <circle
                  stroke="blue"
                  strokeWidth="2"
                  fill="transparent"
                  r={radius}
                  cx="22"
                  cy="22"
                  strokeDasharray={circumference + " " + circumference}
                  strokeDashoffset={offset}
                  style={{
                    transform: "rotate(-90deg)",
                    transformOrigin: "center",
                  }}
                />
                <text
                  x="50%"
                  y="50%"
                  textAnchor="middle"
                  fill="black"
                  fontSize="10px"
                  dominantBaseline="middle"
                >
                  {percentage}%
                </text>
              </svg>
            </div>{" "}
            {/* Percentage circle */}
            <div className="lesson-note">
              {numberOfLearnedLessons}/{totalLessons} lessons
            </div>{" "}
            {
              learningInfo?.status == "COMPLETED" || percentage == 100 ? (<div className="lesson-note" style={{color: 'red', fontWeight: 'bold'}}>Congrats! Please check your email to view certificate</div>) : <></>
            }
            {/* Lesson note */}
          </div>
        </div>
      </header>
      <div className="row">
        {isLoading ? (
          <Loading />
        ) : (
          <>
            {" "}
            <div className="col-md-8 video-container">
              {quizLoading ? (
                <div className="loading">
                  <span>.</span>
                  <span>.</span>
                  <span>.</span>
                </div>
              ) : showQuiz ? (
                <><h1 className="video-title" style={{marginLeft: '100px'}}>{currentLesson.title}</h1>
                <Quiz learnerAnswers={learnerAnswers} quiz={quizData} course={courseDetail} lesson={currentLesson}/></>
              ) : (
                <div id="player">
                  <iframe
                    id="youtubePlayer"
                    title="YouTube video player"
                    width="100%"
                    height="550"
                    src={`https://www.youtube-nocookie.com/embed/${getYouTubeID(
                      currentVideoUrl
                    )}?enablejsapi=1&origin=${window.location.origin}`}
                    frameBorder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen
                    onError={(error) =>
                      console.error("Error loading video:", error)
                    }
                  ></iframe>
                </div>
              )}
            </div>
            <div className="col-md-4 lesson-modules">
              <div
                className="tab-content tab__content__wrapper"
                id="myTabContent"
              >
                <div
                  className="tab-pane fade active show"
                  id="projects__two"
                  role="tabpanel"
                  aria-labelledby="projects__two"
                >
                  <div
                    className="accordion content__cirriculum__wrap"
                    id="accordionExample"
                  >
                    {/* Placeholder for course modules */}

                    {courseDetail.courseModules.map((module, index) => (
                      <div className="accordion-item" key={module.id}>
                        <h2 className="accordion-header" id={`heading${index}`}>
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
                          <div className="module-lessons-container accordion-body">
                            {module.moduleLessons.map((lesson) => {
                              console.log(lesson?.lessonQuizzes?.filter(x => {
                                return x.status != "INACTIVE"
                              }).map((quiz) => {
                                return ((<FaRegFileAlt
                                  key={quiz.id}
                                  onClick={() => handleQuizClick(quiz.id)}
                                  style={{
                                    cursor: "pointer",
                                    marginLeft: "10px",
                                  }}
                                />))
                              }));

                              return (
                                <div
                                  className={`scc__wrap ${
                                    !lessonExistence[lesson.id] ? "undertint" : ""
                                  }${
                                    currentLessonTitle === lesson.title
                                      ? "active-lesson"
                                      : ""
                                  }`}
                                  key={lesson.id}
                                >
                                  <div
                                    className={`scc__info ${
                                      !lessonExistence[lesson.id]
                                        ? "lesson-not-watched"
                                        : ""
                                    }`}
                                  >
                                    {lessonExistence[lesson.id] ? (
                                      <i
                                        className="icofont-video-alt"
                                        onClick={() => setShowQuiz(false)}
                                      />
                                    ) : (
                                      <i className="icofont-lock" />
                                    )}
                                    <h5>
                                      <span>Video :</span> {lesson.title}
                                    </h5>
                                    {
                                    lesson?.lessonQuizzes?.filter(x => {
                                      return x.status != "INACTIVE"
                                    }).map((quiz) => {
                                      return ((<FaRegFileAlt
                                        key={quiz.id}
                                        onClick={() => handleQuizClick(quiz.id)}
                                        style={{
                                          cursor: "pointer",
                                          marginLeft: "10px",
                                        }}
                                      />))
                                    })}
                                  </div>
                                  
                                  <div className="scc__meta flex-container">
                                    {lessonExistence[lesson.id] ? (
                                      <a href={lesson.videoUrl} target="_blank">
                                        <span
                                          className="watch"
                                          style={{ marginBottom: "10px" }}
                                          onClick={(event) => {
                                            event.preventDefault();
  
                                            const videoId = getYouTubeID(
                                              lesson.videoUrl
                                            ); // Use the function to get the video ID
                                            setCurrentVideoUrl(videoId);
                                            setCurrentLessonTitle(lesson.title);
                                            setShowQuiz(false);
                                          }}
                                        >
                                          <i
                                            className="icofont-eye"
                                            onClick={() => setShowQuiz(false)}
                                          />{" "}
                                          Watch
                                        </span>
                                      </a>
                                    ) : (
                                      //Replace buttom that have the update API
                                      <a href={lesson.videoUrl} target="_blank">
                                        <span
                                          className="watch"
                                          style={{
                                            marginBottom: "10px",
                                          }}
                                          onClick={(event) => {
                                            try {
                                              event.preventDefault();
  
                                              const videoId = getYouTubeID(
                                                lesson.videoUrl
                                              ); // Use the function to get the video ID
                                              setCurrentVideoUrl(videoId);
                                              setCurrentLessonTitle(lesson.title);
                                              setShowQuiz(false);
                                              handleUpdateLearnedLesson(
                                                lesson.id
                                              );
                                              console.log(lesson.id);
                                            } catch (error) {
                                              console.error(
                                                "Error updating learned lesson:",
                                                error
                                              );
                                            }
                                          }}
                                        >
                                          <i
                                            className="icofont-eye"
                                            onClick={() => setShowQuiz(false)}
                                          />{" "}
                                          Watch
                                        </span>
                                      </a>
                                    )}
                                  </div>
                                    <div style={{width: '100%'}} className="scc__meta flex-container">
                                    {
                                      lesson?.lessonDocuments?.filter(x => {
                                        return x != null && x.title != ""
                                      })
                                      .map((document) => {
                                        return (
                                          <h5>
                                            <a href={document?.documentUrl} target="_blank">{document?.title}</a>
                                          </h5>
                                        )
                                      })
                                    }
                                    </div>
                                </div>
                              )
                            })}
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </>
        )}
      </div>

      <footer>
        {/* <button
          className="prev-lesson"
          onClick={() => {
            goToPreviousLesson();
            setShowQuiz(false);
          }}
        >
          Previous Lesson
        </button>
        <button
          className="next-lesson"
          onClick={() => {
            goToNextLesson();
            setShowQuiz(false);
          }}
        >
          Next Lesson
        </button> */}

        <p>&copy; {new Date().getFullYear()} Edurock. All Rights Reserved.</p>
      </footer>
    </div>
  );
};

export default LessonDetail;
