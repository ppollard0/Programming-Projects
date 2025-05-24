import React, {useState,useEffect} from 'react';
import {View, Text, StyleSheet} from 'react-native';
import axios from 'axios';
import WeatherTemp from './WeatherTemp';
import WeatherImage from './WeatherImage';
import Extras from './Extras';


function WeatherList() {
    const apiKey = 'e8b0b93f63d8e9076347bd8d069f3f73';
    const [weather, setWeather] = useState({reqDT: 1, description: '', temp: 0, feel: 0, wind: 0, hi: 0, lo: 0, prcp: 0});
    const [zip, setZip] = useState('65802');

    const url = `https://api.openweathermap.org/data/2.5/weather?zip=${zip},us&appid=${apiKey}&units=imperial`;

    useEffect(()=>{
            axios
            .get(url)
            .then(res => {
                setWeather({ ...weather, 
                    reqDT: res.data.dt, 
                    description: res.data.weather[0].description,
                    temp: res.data.main.temp,
                    feel: res.data.main.feels_like,
                    code: res.data.weather[0].id,
                    wind: res.data.wind.speed,
                    hi: res.data.main.temp_max,
                    lo: res.data.main.temp_min,
                    prcp: res.data.main.humidity
                });
            })
            .catch(err=>{
                console.log(err);
            })
    },[zip])
    let DTsec = (weather.reqDT) / 1000
    let DTmin = DTsec / 60
    let DThr = DTmin / 60
    let DTformat = JSON.stringify(parseInt(DThr)) + ":" + JSON.stringify(parseInt(DTmin))
    let dte = new Date()
    let formattedDate = dte.getDay() + '-' + dte.getMonth() + '-' + dte.getDate() + '-' + dte.getFullYear()

    if (weather.reqDT!==1)  
    return (
        <View>
            <View style={styles.today}>
                <WeatherImage code={weather.code}/>
                <WeatherTemp date={formattedDate} temp={weather.temp} feel={weather.feel}/>
            </View>
            <View style={styles.extra}>
                <Extras prcp={weather.prcp} hi={weather.hi} lo={weather.lo} wnd={weather.wind}/>
            </View>
        </View>
    )
    else
    return (
        <View>
            <Text>Loading ...</Text>
        </View>
    ) 
}

const styles = StyleSheet.create({
    txtSze: {
      fontSize: 20,
    },
    txt: {
      alignItems: 'center',
      justifyContent: 'center'
    },
    today: {
      marginTop: 25,
      marginRight: 10,
      display: 'flex',
      flexDirection: 'row',
      alignItems: 'center'
    },
    extra: {
      alignItems: 'center',
    },
  })

export default WeatherList