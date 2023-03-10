# react-native-saltpay

package to integrate with saltpay payments

## Installation

```sh
gh repo clone PedroHSodre/react-native-saltpay

HTTPS https://github.com/PedroHSodre/react-native-saltpay.git
```

## Usage

```js
import { requestPayment, reversePayment } from 'react-native-saltpay';

// ...

const resultPayment = requestPayment('transactionId', 'amount', 'currency');

const resultReverse = reversePayment('transactionId');
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
