import React, { useEffect, useState } from "react";
import "./QuizPage.css";
import axios from "axios";
import { BASE_URL } from "../../data/const";

function Quiz({ quiz, course, lesson, learnerAnswers }) {
  const [questions, setQuestions] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [answers, setAnswers] = useState([]);
  const [showScore, setShowScore] = useState(false);
  const [score, setScore] = useState(0);
  const [startTime, setStartTime] = useState(new Date());
  const [duration, setDuration] = useState(quiz.duration); // Assuming `quiz.duration` is in minutes
  const [timeRemaining, setTimeRemaining] = useState(duration * 60); // in seconds

  useEffect(() => {
    const newDataQuestions = quiz?.quizQuestions?.map((question) => {
      const answerOptions = question.questionAnswers.map((answer) => ({
        id: answer.id,
        answerText: answer.description,
      }));

      return {
        questionId: question.id,
        questionText: question.description,
        type: question.type,
        answerOptions,
      };
    });
    setQuestions(newDataQuestions);
  }, [quiz]);

  useEffect(() => {
    const savedState = localStorage.getItem(`quizState-${quiz.id}`);
    if (learnerAnswers === null && savedState) {
      const { savedAnswers, savedTimeRemaining, savedStartTime } = JSON.parse(savedState);
      setAnswers(savedAnswers);
      setTimeRemaining(savedTimeRemaining);
      setStartTime(new Date(savedStartTime));
    } else if (learnerAnswers === null) {
      setStartTime(new Date());
    }
  
    if (duration === 0) return; // Do not start the timer if duration is 0
  
    const timer = setInterval(() => {
      setTimeRemaining((prevTime) => {
        if (prevTime <= 1) {
          clearInterval(timer);
          handleSubmit();
          return 0;
        }
        const newTime = prevTime - 1;
        localStorage.setItem(
          `quizState-${quiz.id}`,
          JSON.stringify({
            savedAnswers: answers,
            savedTimeRemaining: newTime,
            savedStartTime: startTime,
          })
        );
        return newTime;
      });
    }, 1000);
  
    return () => clearInterval(timer);
  }, [duration, learnerAnswers, quiz.id]);
  
  

  useEffect(() => {
    if (startTime) {
      localStorage.setItem(
        `quizState-${quiz.id}`,
        JSON.stringify({
          savedAnswers: answers,
          savedTimeRemaining: timeRemaining,
          savedStartTime: startTime,
        })
      );
    }
  }, [answers, timeRemaining, startTime, quiz.id]);

  const handleAnswerSelect = (questionIndex, answerIndex) => {
    const newAnswers = [...answers];

    if (!newAnswers[questionIndex]) {
      newAnswers[questionIndex] = {
        questionId: questions[questionIndex].questionId,
        answerIds: [],
        type: questions[questionIndex].type,
      };
    }

    if (questions[questionIndex].type === "SC") {
      newAnswers[questionIndex].answerIds = [answerIndex];
    } else {
      const currentSelection = newAnswers[questionIndex].answerIds || [];
      const answerPosition = currentSelection.indexOf(answerIndex);
      if (answerPosition !== -1) {
        currentSelection.splice(answerPosition, 1);
      } else {
        currentSelection.push(answerIndex);
      }
      newAnswers[questionIndex].answerIds = currentSelection;
    }

    setAnswers(newAnswers);
  };

  const renderQuestions = () => {
    const startIndex = currentPage * 10;
    const endIndex = Math.min(startIndex + 10, questions.length);

    return questions.slice(startIndex, endIndex).map((question, index) => (
      <div style={{ marginBottom: "50px" }} key={startIndex + index}>
        <div className="question-section">
          <div className="question-count">
            <span>Question {startIndex + index + 1}</span>/{questions.length}
          </div>
          <div className="question-text">{question.questionText}</div>
          {learnerAnswers && (
            <div className="answer-section">
              {question.answerOptions.map((answerOption, answerIndex) => (
                <div key={answerOption.id}>
                  <input
                    type={question.type === "MC" ? "checkbox" : "radio"}
                    disabled
                    checked={learnerAnswers.answerData[index]?.questionAnswers[answerIndex]?.isCorrect}
                  />
                  {answerOption.answerText}
                </div>
              ))}
            </div>
          )}
        </div>
        {!learnerAnswers && (
          <div className="answer-section">
            {question.answerOptions.map((answerOption, answerIndex) => (
              <label key={answerOption.id}>
                <input
                  type={question.type === "MC" ? "checkbox" : "radio"}
                  name={`question${startIndex + index}-option${answerOption.id}`}
                  checked={
                    question.type === "MC"
                      ? answers[startIndex + index]?.answerIds?.includes(answerOption.id)
                      : answers[startIndex + index]?.answerIds?.[0] === answerOption.id
                  }
                  onChange={() => handleAnswerSelect(startIndex + index, answerOption.id)}
                />
                {answerOption.answerText}
              </label>
            ))}
          </div>
        )}
        {learnerAnswers && (
          <div className="correct-answer-section">
            <p>Correct Answer(s):</p>
            <ul>
              {learnerAnswers.answerData[index]?.questionAnswers
                .filter((answer) => answer.isCorrect)
                .map((correctAnswer, correctIndex, correctAnswers) => (
                  <span key={correctAnswer.id}>
                    {correctAnswer.description}
                    {correctIndex !== correctAnswers.length - 1 && ", "}
                  </span>
                ))}
            </ul>
          </div>
        )}
      </div>
    ));
  };

  const handleSubmit = () => {
    const isAnyAnswerNull = answers.some((answer) => answer === null);

    if (isAnyAnswerNull) {
      alert("Please answer all questions before submitting.");
      return;
    }

    const user = localStorage.getItem("USER");
    const endTime = new Date();

    const requestAnswer = {
      lessonId: lesson.id,
      courseId: course.id,
      answers: JSON.stringify(answers),
      learnerId: JSON.parse(user)?.userId,
      quizId: quiz.id,
      createdDate: startTime.toISOString(),
      submitTime: endTime.toISOString(),
    };

    axios
      .post(BASE_URL + "api/v1/learner-answer", requestAnswer)
      .then((response) => {
        if (response.status === 200) {
          localStorage.removeItem(`quizState-${quiz.id}`);
          window.location.reload();
        }
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? "0" : ""}${remainingSeconds}`;
  };

  const formatTimeDifference = (endTime, startTime) => {
    const diffInSeconds = Math.floor((endTime - startTime) / 1000);
    const minutes = Math.floor(diffInSeconds / 60);
    const remainingSeconds = diffInSeconds % 60;

    if (minutes <0 || remainingSeconds < 0) {
      return `Less than 1 minutes`;
    }

    return `${minutes} minutes and ${remainingSeconds} seconds`;
  };

  return (
    <div className="quiz-container">
      {learnerAnswers && (
        <div style={{ marginBottom: "40px" }}>
          <p>
            <span style={{ fontWeight: "bold" }}>Score:</span> {learnerAnswers.score}
          </p>
          <p>
            <span style={{ fontWeight: "bold" }}>Correct Answer(s):</span> {learnerAnswers.correctAnswers}/{quiz.quizQuestions.length}
          </p>
          {!learnerAnswers.isRetake && (
              <p>
                <strong>Time Taken:</strong> {formatTimeDifference(new Date(learnerAnswers.submitTime), new Date(learnerAnswers.createdDate))}
              </p>
            )}
        </div>
      )}
      {!showScore ? (
        <>
          {duration > 0 && (learnerAnswers === null || learnerAnswers?.isRetake) && (
            <div className="timer">
              Time Remaining: {formatTime(timeRemaining)}
            </div>
          )}
          {renderQuestions()}
          <div className="button-container">
            {!learnerAnswers && (
              <button onClick={() => setAnswers(Array(questions.length).fill(null))}>
                Clear
              </button>
            )}
            {!learnerAnswers && (
              <button onClick={handleSubmit}>Submit</button>
            )}
          </div>
        </>
      ) : (
        <div className="score-section">
          {!learnerAnswers && (
            <button className="return-button">
              Return
            </button>
          )}
        </div>
      )}
    </div>
  );
}

export default Quiz;
