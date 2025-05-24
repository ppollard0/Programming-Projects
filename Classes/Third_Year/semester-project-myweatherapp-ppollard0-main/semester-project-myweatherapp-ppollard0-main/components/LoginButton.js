/*
LoginButton:
Pressable button to send your login info when you're finished typing.
*/
import React from 'react';
import {View, Text, StyleSheet, Pressable} from 'react-native';

function LoginButton({onPress}) {
  return (
    <Pressable onPress={onPress} style={styles.container}>
        <Text style={styles.buttonTxt}>Login</Text>
    </Pressable>
  )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: '#A0A0A0',
        maxHeight: '10%',
        width: '30%',
        borderRadius: 20
    },
    buttonTxt: {
        fontSize: 30
    }
  });

export default LoginButton