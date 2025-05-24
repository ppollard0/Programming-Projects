/*
Journal Screen:
Shows Login and Journal screens. Starts at Login and switches to Journal 
after logging in
*/
import React, {useEffect, useState} from 'react';
import { View, Text, StyleSheet, TextInput, ScrollView, TouchableOpacity} from 'react-native';
import LoginButton from '../components/LoginButton';
import * as SecureStore from "expo-secure-store";
import axios from "axios";
import Login from "../components/Login";
import JournalCard from '../components/JournalCard';
import LoadJournal from '../components/LoadJournal';


function Journal() {
  // Set inital States
  const [loggedIn, setLoggedIn] = useState(false);
  const [emailInput, setEmailInput] = useState("");
  const [passInput, setPassInput] = useState("");
  const [curTkn, setCurTkn] = useState("");
  const [journalPge, setJournalPge] = useState(false);
  var Lst = []

  // Go to journal Page
  const JournalPge = () => {
    setJournalPge (true);
  }

  const BackUp = () => {
    setJournalPge (false);
  }

  // Check Token
  const checkToken = async() => {
    let token = await getToken();
    if(token){
      tokenExp(token)
      setLoggedIn (true);
      setCurTkn(token);
    }
    else{
      //console.warn('Incorrect User/Pass')
      setLoggedIn (true);
    }
  }

  // Call to server when logging in
  const LogCall = () => {
    let email = emailInput
    let pass = passInput
    Login(email, pass);
    checkToken();
  }

  // Get Token if it's there
  async function getToken(key) {
    let result = await SecureStore.getItemAsync('Token');
    console.log(result)
    if (result) {
      return result
    }
    else {
      console.log('No Token')
    }
  }

  // Check if the Token is expired
  async function tokenExp(tkn) {
    let config = {
      method: 'get',
      url: 'http://10.15.3.139:3000/api/data/load_data',
      headers: {
        'Content-Type': 'x-auth',
        'Content': tkn
      }
    };

    axios
        .request(config)
        .await((response) => {
            console.log(JSON.stringify(response.data));
        })
        .catch((error) => {
            console.log(error);
        });

    return response
  }

  // Goes to Journal if logged in. Otherwise takes you to login.
  if (loggedIn) {
    // Select page for data
    if (journalPge){
      return (
        <View style={styles.container2}>
            <View style={styles.head}>
                <Text style={styles.headTxt}>Title</Text>
                <TouchableOpacity style={styles.back} onPress={BackUp}>
                  <Text style={styles.headTxt}>Back</Text>
                </TouchableOpacity>
            </View> 
            <View style={styles.data}>
                <View style={styles.row}>
                    <Text style={styles.txt}>Temp: 95&deg;F</Text>
                    <Text style={styles.txt}>High: 104&deg;F</Text>
                    <Text style={styles.txt}>Low: 85&deg;F</Text>
                </View>
                <View style={styles.row}>
                    <Text style={styles.txt}>Type: Sunny</Text>
                    <Text style={styles.txt}>Wind: 39mph</Text>
                </View>
                <View style={styles.row}>
                    <Text style={styles.txt}>Feel: 98&deg;F</Text>
                </View>
            </View>
            <View style={styles.desc}>
                <Text style={styles.txt}>Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Nullam dolor mauris, ornare eu commodo nec, pellentesque nec metus. 
            Sed ipsum mauris, condimentum ut augue id, dictum egestas nulla. 
            Sed sit amet urna eu purus molestie eleifen. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Nullam dolor mauris, ornare eu commodo nec, pellentesque nec metus. 
            Sed ipsum mauris, condimentum ut augue id, dictum egestas nulla. 
            Sed sit amet urna eu purus molestie eleifen</Text>
            </View>
            <View style={styles.head}>
                <Text style={styles.headTxt}>Images</Text>
            </View>
            <View style={styles.img}></View>
        </View>
      )
    }
    else {
      return (
        <View style={styles.container}>
          <ScrollView>
            <TouchableOpacity onPress={ () => JournalPge}>
              <JournalCard title={"Title"} date={"Date"} desc={"Desc"}/>
            </TouchableOpacity>
            <TouchableOpacity onPress={ () => JournalPge()}>
              <JournalCard title={"Title"} date={"Date"} desc={"Desc"}/>
            </TouchableOpacity>
            <TouchableOpacity onPress={ () => JournalPge()}>
              <JournalCard title={"Title"} date={"Date"} desc={"Desc"}/>
            </TouchableOpacity>
            <TouchableOpacity onPress={ () => JournalPge()}>
              <JournalCard title={"Title"} date={"Date"} desc={"Desc"}/>
            </TouchableOpacity>
            <TouchableOpacity onPress={ () => JournalPge()}>
              <JournalCard title={"Title"} date={"Date"} desc={"Desc"}/>
            </TouchableOpacity>
          </ScrollView>
        </View>
      )
    }
  }
  else {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Login</Text>
        <View style={styles.info}>
            <TextInput 
              style={styles.infoTxt} 
              placeholder="Username"
              id="email"
              value={emailInput}
              onChangeText={(text) => setEmailInput(text)}
              />
            <TextInput 
              style={styles.infoTxt} 
              placeholder="Password"
              id="password"
              value={passInput}
              onChangeText={(text) => setPassInput(text)}
              />
        </View>
        <LoginButton onPress={LogCall}/>
      </View>
    )
  }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    txtSze: {
      fontSize: 20,
      alignItems: 'center',
      justifyContent: 'center'
    },
    infoTxt: {
      padding: 10,
      fontSize:20
    },
    info: {
      marginVertical: 20
    },
    title: {
      fontSize: 50,
    },

    // Journal Page

    container2:{
      flex: 1,
    },
    head: {
        alignItems: 'flex-start',
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-around'
    },
    headTxt: {
        fontSize: 24
    },
    txt: {
        fontSize: 16,
    },
    data: {
        height: '20%',
        width: '90%',
        backgroundColor: '#c0c0c0',
        boerderColor: '000',
        borderWidth: 2,
        margin: 5
    },
    row: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-evenly',
        marginVertical: 10
    },
    desc: {
        height: '40%',
        width: '90%',
        backgroundColor: '#c0c0c0',
        boerderColor: '000',
        borderWidth: 2,
        margin: 5
    },
    img: {},
    imgSze: {
        height: 100,
        width: 50
    },
    back: {
      justifyContent: 'flex-end'
    }
  });

export default Journal