package com.dogukan.liveerror.validator

interface Validator {
    fun validateSignUp(field: String): Int?
}