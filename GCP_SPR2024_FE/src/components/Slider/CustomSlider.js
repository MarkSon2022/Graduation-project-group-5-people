import React from 'react';
import PropTypes from 'prop-types';

const CustomSlider = ({ min, max, value, onChange, marks }) => {
  const handleChange = (e) => {
    const newValue = parseInt(e.target.value, 10);
    onChange(newValue);
  };

  return (
    <div className="custom-slider">
      <input
        type="range"
        min={min}
        max={max}
        value={value}
        onChange={handleChange}
        className="slider"
      />
      <div className="slider-labels">
        {marks.map((mark) => (
          <div key={mark.value} style={{ left: `${(mark.value / max) * 100}%` }} className="slider-mark">
            {mark.label}
          </div>
        ))}
      </div>
      <style jsx>{`
        .custom-slider {
          position: relative;
          width: 100%;
        }
        .slider {
          width: 100%;
        }
        .slider-labels {
          display: flex;
          justify-content: space-between;
          position: absolute;
          top: 20px;
          width: 100%;
        }
        .slider-mark {
          position: absolute;
          transform: translateX(-50%);
        }
      `}</style>
    </div>
  );
};

CustomSlider.propTypes = {
  min: PropTypes.number.isRequired,
  max: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
  onChange: PropTypes.func.isRequired,
  marks: PropTypes.arrayOf(
    PropTypes.shape({
      value: PropTypes.number.isRequired,
      label: PropTypes.string.isRequired,
    })
  ).isRequired,
};

export default CustomSlider;
