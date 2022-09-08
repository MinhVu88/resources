import React from 'react';

const Wheel = ({ wheelPositionLeft, wheelPositionTop }) => {
  const wheelStyle = {
    position: "absolute",
    width: "60px",
    height: "110px",
    background: "#000",
    borderRadius: "40px",
    left: wheelPositionLeft,
    top: wheelPositionTop,
  }

  return (
    <div style={wheelStyle} />
  )
}

export default Wheel;