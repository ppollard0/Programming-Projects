import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

function JournalTest2() {
  return (
    <View style={styles.container}>
        <View style={styles.head}>
            <Text style={styles.headTxt}>Title</Text>
        </View> 
        <View style={styles.data}>
            <View style={styles.row}>
                <Text style={styles.txt}>Words</Text>
                <Text style={styles.txt}>Words</Text>
                <Text style={styles.txt}>Words</Text>
            </View>
            <View style={styles.row}>
                <Text style={styles.txt}>Words</Text>
                <Text style={styles.txt}>Words</Text>
            </View>
            <View style={styles.row}>
                <Text style={styles.txt}>Words</Text>
            </View>
        </View>
        <View style={styles.desc}>
            <Text style={styles.txt}>Words</Text>
        </View>
        <View style={styles.head}>
            <Text style={styles.headTxt}>Images</Text>
        </View>
        <View style={styles.img}></View>
    </View>
  )
}

const styles = StyleSheet.create({
    container2:{
        flex: 1,
    },
    head: {
        alignItems: 'flex-start'
    },
    headTxt: {
        fontSize: 24
    },
    txt: {
        fontSize: 14,
    },
    data: {
        height: '20%',
        width: '90%',
        backgroundColor: '#c0c0c0',
        boerderColor: '000',
        borderWidth: 2,
        borderRadius: 5
    },
    row: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-evenly'
    },
    desc: {
        height: '20%',
        width: '90%',
        backgroundColor: '#c0c0c0',
        boerderColor: '000',
        borderWidth: 2,
        borderRadius: 5
    },
    img: {},
    imgSze: {
        height: 100,
        width: 50
    }

})

export default JournalTest2