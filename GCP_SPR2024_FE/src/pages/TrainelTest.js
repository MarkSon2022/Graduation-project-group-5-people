import React, { useRef } from "react";
import { Link } from "react-router-dom/cjs/react-router-dom";
import "../styles/TrainelTest.css";

import { useFormik } from "formik";
import { GetQuizAction } from "../redux/actions/QuizAction";
import { useDispatch } from "react-redux";
import { history } from "../App";
const TrainelTest = () => {
const dispatch = useDispatch()
const formik= useFormik({
  initialValues:{
    id: '',
  },
  onSubmit:(value) =>{
    console.log(value)
    const action = GetQuizAction(value.id, history);
    
    dispatch(action)
  }
})
  return (
    <div className="container container-quiz text-center" style={{height:'500px'}}>
      <h1 className="title">Quizzes Free</h1>

      <ul className="des">
        <li>You will be asked 10 questions one after another</li>
        <li>10 points is awarded for the connect answer</li>
      </ul>

      <form id="form" className=" mt-2" onSubmit={formik.handleSubmit}>
        <input name="id" onChange={formik.handleChange}  type="text" placeholder="Input your name!" />

      <div className="start">
        <button type="submit" className="btn btn-success pointer-event" >Start</button>
      </div>
      </form>
    </div>
  );
};

export default TrainelTest;
