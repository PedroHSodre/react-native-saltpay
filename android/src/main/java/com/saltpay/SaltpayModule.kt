package com.saltpay

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

import co.saltpay.epos.integrationlib.EposRequestDispatcherApi
import co.saltpay.epos.integrationlib.EposResponseListener

import co.saltpay.epos.integrationlib.common.SentSuccessfully
import co.saltpay.epos.integrationlib.common.FailureSendingRequest

import co.saltpay.epos.models.common.Currency

import co.saltpay.epos.models.request.SalePayment
import co.saltpay.epos.models.request.PayAppConfigRequest

import co.saltpay.epos.models.response.SalePaymentResponse
import co.saltpay.epos.models.response.ResponseModel
import co.saltpay.epos.models.response.PayAppConfigResponse

import java.math.BigDecimal
import java.util.UUID

import android.widget.Toast

import androidx.fragment.app.Fragment

class SaltpayModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  private var reactContext: ReactApplicationContext = reactContext

  override fun getName(): String {
    return NAME
  }

  lateinit var eposConfiguration: PayAppConfigResponse

  lateinit var requestDispatcher: EposRequestDispatcherApi


  object PayAppResponseListener: EposResponseListener {
    override fun onResponse(response: ResponseModel) {
      when (response) {
        is PayAppConfigResponse -> {

        }
        is SalePaymentResponse.Approved -> {

        }
        is SalePaymentResponse.Failed -> {

        }
        else -> {

        }
      }
    }
  }

  @ReactMethod
  fun dispatcher(requestId: String, amount: String, currency: String) {

    val requestModel = SalePayment(
      UUID.randomUUID().toString(),
      BigDecimal(amount),
      eposConfiguration.availableCurrencies.first()
//      Currency("EUR", "1245", 1),
    )

    requestDispatcher.request(requestModel)
  }

  override fun initialize() {

    super.initialize()

    // Add the following.
    requestDispatcher = EposRequestDispatcherApi.init(
      reactContext.currentActivity!!.application,
      object : EposResponseListener {
        override fun onResponse(response: ResponseModel) {
          when (response) {
            is PayAppConfigResponse -> {
              eposConfiguration = response
            }
            is SalePaymentResponse.Approved -> {

            }
            is SalePaymentResponse.Failed -> {

            }
            else -> {

            }
          }
        }
      }
    )

    val payAppConfig = PayAppConfigRequest(UUID.randomUUID().toString())
    val result = requestDispatcher.request(payAppConfig)

    when (result) {
      is SentSuccessfully -> Unit //no op: pay app will get the request
      is FailureSendingRequest.HandlerAppNotAvailable -> {
        Toast.makeText(reactContext.currentActivity, "Install a supported pay app!", Toast.LENGTH_LONG).show()
      }
      is FailureSendingRequest.Unknown -> {
        println("Error making request")
        result.throwable.printStackTrace()
      }
      else -> {

      }
    }
  }

  companion object {
    const val NAME = "Saltpay"
  }
}
