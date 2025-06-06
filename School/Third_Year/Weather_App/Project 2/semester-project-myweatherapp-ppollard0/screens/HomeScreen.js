import React from 'react';
import { View, Text, StyleSheet, Image} from 'react-native';
import Next from '../components/Next';
import Date from '../components/Date';
import HookCounter from '../components/HookCounter';
import WeatherList from '../components/WeatherList';




function HomeScreen() {
  return (
    <View style={styles.container}>
      <View style={styles.topTxt}>
        <Text style={styles.txtSze}>Location</Text>
      </View>
      <View >
          <WeatherList/>
      </View>
      <View style={styles.next}>
        <Next day="Mon" code="200" Hi="2" Lo="1" pcnt="2"/>
        <Next day="Tue" code="300" Hi="3" Lo="4" pcnt="3"/>
        <Next day="Wed" code="400" Hi="4" Lo="3" pcnt="4"/>
        <Next day="Thu" code="801" Hi="1" Lo="2" pcnt="1"/>
      </View>
    </View>
  )
}
const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
    },
    txtSze: {
      fontSize: 20,
      alignItems: 'center',
      justifyContent: 'center'
    },
    topTxt: {
      alignItems: 'center',
    },
    tempText:{},
    extra: {
      alignItems: 'center',
    },
    next: {
      display: 'flex',
      flexDirection: 'row',
      justifyContent: 'space-evenly',
    },
  });

export default HomeScreen