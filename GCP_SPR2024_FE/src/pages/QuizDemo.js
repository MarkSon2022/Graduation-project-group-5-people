import React, { useEffect } from 'react'
import QuizQuestion from './QuizQuestion';
import "../styles/TrainelTest.css";
import { useSelector } from 'react-redux';
function QuizDemo() {

    const state = useSelector(state => state)
    

    useEffect(() => {
        console.log(state);
    })

    function onNext(){
        console.log("On next");
    }

    function onPrev(){
        console.log("On Prev");
    }
  return (
    <div className='container container-quiz'>
        <h1 className='title'>Quiz Application</h1>
        
        <div className='quiz-question'>
            <QuizQuestion/>
        </div>

        <div className='PrevAndNext'>
            <button onClick={onPrev} className='btn btn-secondary'>Prev</button>
            <button onClick={onNext} className='btn btn-primary'>Next</button>
        </div>
    </div>
  )
}

export default QuizDemo