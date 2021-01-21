package com.example.remainder_for_android.request


class LoginData: BaseRequest {
    val mail_address: String;
    val password: String;
    constructor(mailAddress: String, password: String) {
        this.mail_address = mailAddress
        this.password = password
    }
}
