/*

*/
import React from 'react'
import { Text, View, Image, StyleSheet, ActivityIndicator, FlatList } from 'react-native'
import axios from "axios";
import * as SecureStore from "expo-secure-store"

export function Login(email, password) {
    let data = JSON.stringify({
        email: email,
        password: password
      });
    
    let config = {
      post: {
        method: 'post',
        url: 'http://10.15.3.139:3000/api/user/auth',
        headers: { 
          'Content-Type': 'application/json'
        },
        data
      },
    };
    console.log(data)
    axios
        .request(config.post)
        .then((response) => {
            const tokenResponse = response.data;
            SecureStore.setItemAsync("Token", tokenResponse.token);
        })
        .catch((Error)=> {
            console.log(Error)
        });
}

export default Login