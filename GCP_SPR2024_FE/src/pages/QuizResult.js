import React from 'react'
import { Link } from 'react-router-dom/cjs/react-router-dom'
import ResultTable from './ResultTable';
import '../styles/TrainelTest.css'

export default function QuizResult() {
  function onRestart() {
    console.log("on restart");
  }
  return (
    <div className='container-quiz container'>
        <h1>Quiz Application</h1>

        <div className='table-result container'>
            <div className='result'>
                <span>UserName</span>
                <span className='bold'>Daily Tuition</span>
            </div>
            <div className='result'>
              <span>Total Quiz Points : </span>
              <span className='bold'>50</span>
            </div>
            <div className='result'>
              <span>Total Question : </span>
              <span className='bold'>05</span>
            </div>
            
            <div className='result'>
              <span>Total Earn Points : </span>
              <span className='bold'>30</span>
            </div>
            <div className='result'>
              <span>Quiz Results : </span>
              <span className='bold'>Passed</span>
            </div>

            <div className='container mt-5'>
              <ResultTable/>
            </div>
            
            <div className='start mt-5'>
              <Link className='btn btn-success' to={'/'} onClick={onRestart}>Back to Home</Link>
            </div>

            
        </div>
    </div>
  )
}
