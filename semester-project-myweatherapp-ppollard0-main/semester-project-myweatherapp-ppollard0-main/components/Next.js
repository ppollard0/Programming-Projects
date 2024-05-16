import React from 'react'
import {View, Text, StyleSheet, Image} from 'react-native';

function Next(props) {
  const cloudy = '../assets/weather/Small/cloudy.png'
  const sunny = '../assets/weather/Small/sunny.png'
  const rainy = '../assets/weather/Small/rainy.png'
  const stormy = '../assets/weather/Small/stormy.png'
  const snowy = '../assets/weather/Small/snowy.png'
  const partlyCloudy = '../assets/weather/Small/partlyCloudy.png'
  let weVal = (props.code)
  if (weVal < "300") {
    return (
      <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(stormy)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
      </View>
    )
  }
  else if (weVal < "600") {
    return (
      <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(rainy)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
      </View>
    )
  }
  else if (weVal < "700") {
    return (
      <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(snowy)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
      </View>
    )
  }
  else if (weVal > 700 && weVal != "800" && weVal != "801") {
    return (
      <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(cloudy)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
      </View>
    )
  }
  else if (weVal = "800") {
    return (
      <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(sunny)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
      </View>
    )
  }
  else if (weVal = "801") {
    return (
      <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(partlyCloudy)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
      </View>
    )
  }
  return (
    <View style={styles.nextText}>
        <Text style={styles.txtSze}>{props.day}</Text>
          <Image source={require(cloudy)}/>
        <Text style={styles.txtSze}>{props.Hi}&deg;</Text>
        <Text style={styles.txtSze}>{props.Lo}&deg;</Text>
        <Text style={styles.txtSze}>{props.pcnt}%</Text>
    </View>
  )
}

const styles = StyleSheet.create({
    txtSze: {
      fontSize: 20,
    },
    nextText: {
      padding: 25,
      alignItems: 'center',
      justifyContent: 'center'
    },
  })

export default Next