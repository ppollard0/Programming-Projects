import React from 'react'
import Journal from '../screens/Journal';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';

function JournalCard(props) {
  return (
    <View style={styles.container} onPress={Journal.JournalPge}>
        <View style={styles.top}>
            <Text style={styles.topText}>Icon</Text>
            <Text style={styles.topText}>{props.title}</Text>
            <Text style={styles.topText}>{props.date}</Text>
        </View>
        <Text style={styles.bottomText}>{props.desc}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
    container: {
        width: 350,
        height: 125,
        backgroundColor: '#c0c0c0',
        margin: 10
    },
    top: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginHorizontal: 10
    },
    topText: {
        fontSize: 24
    },
    bottomText: {
        fontSize: 18,
        margin: 10,
    }
  });

export default JournalCard