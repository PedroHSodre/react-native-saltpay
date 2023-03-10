import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-saltpay' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const Saltpay = NativeModules.Saltpay
  ? NativeModules.Saltpay
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function requestPayment(
  requestId: String,
  amount: string,
  currency: string
): any {
  return Saltpay.requestPayment(requestId, amount, currency);
}

export function reversePayment(requestId: String): any {
  return Saltpay.reversePayment(requestId);
}
