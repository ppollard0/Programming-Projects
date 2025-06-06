import React from 'react'
import {View, Text, StyleSheet,} from 'react-native';

function WeatherTemp(props) {
  return (
    <View>
      <Text style={styles.txtSze}>{props.date}</Text>
      <Text style={styles.tempText}>{props.temp}&deg;</Text>
      <Text style={styles.txtSze}>Feels like: {props.feel}&deg;</Text>
    </View>
    
  )
}

const styles = StyleSheet.create({
  txtSze: {
    fontSize: 18,
  },
  tempText: {
    fontSize: 48,
  }
})

export default WeatherTemp