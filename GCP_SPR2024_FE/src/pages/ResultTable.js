import React from 'react'

export default function ResultTable() {
  return (
    <div>
        <table className='table table-dark table-hover'>
            <thead className=''>
                <tr>
                    <td>Name</td>
                    <td>Attemps</td>
                    <td>Earn Points</td>
                    <td>Result</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Daily Tuition</td>
                    <td>03</td>
                    <td>20</td>
                    <td>Passed</td>
                </tr>
            </tbody>
        </table>
    </div>
  )
}
