import React from 'react'
import {View, Text, StyleSheet,} from 'react-native';

function Extras(props) {
  return (
    <View style={styles.center}>
        <View>
          <Text style={styles.txtSze}>{props.prcp}%</Text>
        </View>
        <View style={styles.extraToday}>
            <Text style={styles.txtSze}>{props.hi}&deg;</Text>
            <Text style={styles.txtSze}>{props.lo}&deg;</Text>
            <Text style={styles.txtSze}>{props.wnd}mph</Text>
        </View>
    </View>
  )
}

const styles = StyleSheet.create({
    center: {
        alignItems: 'center',
      },
    txtSze: {
      fontSize: 20,
      marginHorizontal: 20,
      alignItems: 'center',
      justifyContent: 'center'
    },
    extraToday: {
      display: 'flex',
      flexDirection: 'row',
      alignContent: 'space-between',
      padding: 20,
    },
  })

export default Extras