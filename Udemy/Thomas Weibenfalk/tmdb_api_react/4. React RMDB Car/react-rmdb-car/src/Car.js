import React, { useState } from 'react';
import Body from './Body';
import Wheel from './Wheel';

const Car = () => {
  // Car state represents the color of the car
  const [color, _setColor] = useState("red");

  return (
  <>
    <Wheel wheelPositionLeft="90px" wheelPositionTop="100px" />
    <Wheel wheelPositionLeft="90px" wheelPositionTop="300px" />
    <Body color={color} />
    <Wheel wheelPositionLeft="360px" wheelPositionTop="100px" />
    <Wheel wheelPositionLeft="360px" wheelPositionTop="300px" />
  </>
  )
};

export default Car;
