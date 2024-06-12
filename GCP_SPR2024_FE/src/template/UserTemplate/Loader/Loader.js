import React from 'react'
  export default function Loader() {
  return (
  <div id="back__preloader">
    <div id="back__circle_loader" />
    <div className="back__loader_logo">
      <img loading="lazy" src="./img/pre.png" alt="Preload" />
    </div>
  </div>
  )
}

