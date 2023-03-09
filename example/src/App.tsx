import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { dispatcher } from 'react-native-saltpay';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  console.log(dispatcher)
  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <TouchableOpacity onPress={() => dispatcher('123456', '14.55', 'EUR')}>
        <Text>dispatch</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
