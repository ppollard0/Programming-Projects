import React from 'react'
import {StyleSheet, Image} from 'react-native';


function WeatherImage(props) {
  const cloudy = '../assets/weather/Large/cloudy.png'
  const sunny = '../assets/weather/Large/sunny.png'
  const rainy = '../assets/weather/Large/rainy.png'
  const stormy = '../assets/weather/Large/stormy.png'
  const snowy = '../assets/weather/Large/snowy.png'
  const partlyCloudy = '../assets/weather/Large/partlyCloudy.png'
  let weVal = (props.code)
  if (weVal < "300") {
    return (
      <Image source={require(stormy)}/>
    )
  }
  else if (weVal < "600") {
    return (
      <Image source={require(rainy)}/>
    )
  }
  else if (weVal < "700") {
    return (
      <Image source={require(snowy)}/>
    )
  }
  else if (weVal > 700 && weVal != "800" && weVal != "801") {
    return (
      <Image source={require(cloudy)}/>
    )
  }
  else if (weVal = "800") {
    return (
      <Image source={require(sunny)}/>
    )
  }
  else if (weVal = "801") {
    return (
      <Image source={require(partlyCloudy)}/>
    )
  }
}

export default WeatherImage