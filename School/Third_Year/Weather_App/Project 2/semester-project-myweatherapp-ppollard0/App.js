import { StatusBar } from 'expo-status-bar';
import { SafeAreaView, StyleSheet, Text, View } from 'react-native';
import { NavigationContainer, StackActions } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from '@expo/vector-icons/Ionicons';

import HomeScreen from './screens/HomeScreen';
import Location from './screens/Location';
import Journal from './screens/Journal';
import JournalTest from './screens/JournalTest';
import JournalTest2 from './screens/JournalTest2';

const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator();

const App =() => {
    return (
        <NavigationContainer>
          <Tab.Navigator
            screenOptions={({route}) => ({
              tabBarIcon: ({focused, color, size}) => {
                let iconName;
                if (route.name ==='Home') {
                  iconName = focused 
                    ? 'ios-home'
                    : 'ios-home';
                } else if (route.name==='Location') {
                  iconName = focused ? 'ios-list' : 'ios-list';
                } else if (route.name==='Journal') {
                  iconName = focused ? 'journal-outline' : 'journal-outline';
                }
                return <Ionicons name={iconName} size={size} color={color} />
              },
              tabBarActiveTintColor: '#000000',
              tabBarInactiveTintColor: '#000000',
              tabBarActiveBackgroundColor: '#C0C0C0',
              tabBarInactiveBackgroundColor: '#A0A0A0'
            })}
          >
            <Tab.Screen
              name="Home"
              component={HomeScreen}
              options={ {
                title: 'MyWeather', 
                headerStyle: {
                  backgroundColor: '#C0C0C0',
                },
                headerTitleStyle: {
                  color: '#fff',
                },
              }}
            />
            <Tab.Screen 
              name ='Location'
              component={Location}
              options={ {
                title: 'Locations',
                headerStyle: {
                  backgroundColor: '#C0C0C0',
                },
                headerTitleStyle: {
                  color: '#fff',
                },
              }} 
            />
            <Tab.Screen 
              name='Journal'
              component={Journal}
              options={ {
                title: 'Journal',
                headerStyle: {
                  backgroundColor: '#C0C0C0'
                },
                headerTitleStyle: {
                  color: '#fff',
                },
              }}
            />
          </Tab.Navigator>
        </NavigationContainer>
    );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default App;