package com.example.remainder_for_android.request

class FCMTokenData: BaseRequest {
    val fcm_token: String;
    constructor(fcmToken: String) {
        this.fcm_token = fcmToken
    }
}