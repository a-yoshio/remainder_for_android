package com.example.remainder_for_android.utils

import java.lang.RuntimeException

class ValidationError(val valueName: String, val msg: String): RuntimeException(valueName + " " + msg)

class ValueEmptyError(val valueName: String, val msg: String = ""): RuntimeException(valueName + " is Empty or Null." + msg)
