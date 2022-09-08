import React from 'react';

const Body = ({ color }) => {
  const bodyStyle = {
    position: "absolute",
    width: "200px",
    height: "400px", 
    background: color,
    borderRadius: "40px",
    left: "155px",
    top: "50px",
  }

  return (
    <div style={bodyStyle} />
  )
}

export default Body;