package com.example.remainder_for_android.model

data class ResultHolder<T>(val targetModel: T, var isSuccess: Boolean = true, var message: String = "")