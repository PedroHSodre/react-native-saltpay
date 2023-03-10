import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { reversePayment, requestPayment } from 'react-native-saltpay';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  console.log(dispatcher)
  return (
    <View style={styles.container}>
      <Text>Teste</Text>
      <TouchableOpacity onPress={() => requestPayment('123456', '14.55', 'EUR')}>
        <Text>requestPayment</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => reversePayment('123456')}>
        <Text>reversePayment</Text>
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
