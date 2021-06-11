package com.dogukan.liveerror.validator

import com.dogukan.liveerror.R
import com.dogukan.liveerror.common.enums.EnumValidator
import com.dogukan.liveerror.common.extension.validateEmail
import com.dogukan.liveerror.common.extension.validateLong
import com.dogukan.liveerror.common.extension.validateShort

class EmailValidator : Validator {

    override fun validateSignUp(field: String) = when {
        field.isEmpty() -> R.string.emailEmptyError
        field.validateEmail() -> R.string.emailContainError
        field.validateLong(EnumValidator.MaxLengthEmail.constraint) -> R.string.emailLongError
        field.validateShort(EnumValidator.MinLengthEmail.constraint) -> R.string.emailShortError
        else -> null
    }
}