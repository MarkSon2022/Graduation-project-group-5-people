import React, { useEffect, useState } from "react";
import data from "../data/question";
import "../styles/TrainelTest.css";

export default function QuizQuestion() {
  const [checked, setChecked] = useState(undefined);

  const question = data[0];

  useEffect(() => {
    console.log(question);
  });

  function onSelect(event) {
    setChecked(event.target.value);
    console.log("check");
  }
  return (
    <div>
      <h2>{question.question}</h2>
      <div className="question">
        <ul key={question.id}>
          {question.options.map((q, index) => (
            <li key={index}>
              <input
                type="radio"
                value={false}
                name="option"
                id={`q${index}-option`}
                onChange={onSelect}
              />

              <label className="text-primary" htmlFor={`q${index}-option`}>
                {q}
              </label>
              <div className="check"></div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
