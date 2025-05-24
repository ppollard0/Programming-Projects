import React from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';
import JournalCard from '../components/JournalCard';

function JournalTest() {
  return (
    <View style={styles.container}>
      <ScrollView>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
          <JournalCard/>
      </ScrollView>
    </View>
  )
}

const styles = StyleSheet.create({
    container: {
      justifyContent:'center',
      alignItems: 'center'
    },
  });

export default JournalTest