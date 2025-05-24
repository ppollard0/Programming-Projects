// Author: Paul Pollard    Date: 2-7-2023
//
//Example of using state
import React, {useState} from 'react'
import { View, Text, Button } from 'react-native'

function HookCounter() {
    const [count, setCount] = useState(0)
  return (
    <View>
        <Text>Count is: {count}</Text>
        <Button onPress={() => {setCount(count + 1)}} title='Up' />
        <Button onPress={() => {setCount(count - 1)}} title='Down' />
    </View>
  )
}

export default HookCounter