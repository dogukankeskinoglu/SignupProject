package com.dogukan.liveerror.validator

import com.dogukan.liveerror.R
import com.dogukan.liveerror.common.enums.EnumValidator
import com.dogukan.liveerror.common.extension.validateLong
import com.dogukan.liveerror.common.extension.validateShort

class UsernameValidator : Validator {
    override fun validateSignUp(field: String) = when {
        field.isEmpty() -> R.string.usernameEmptyError
        field.validateIdentifier() -> R.string.usernameContainError
        field.validateShort(EnumValidator.MinLengthUsername.constraint) -> R.string.usernameShortError
        field.validateLong(EnumValidator.MaxLengthUsername.constraint) -> R.string.usernameLongError
        else -> null
    }

    private fun String.validateIdentifier() =
        !(all { it.isLowerCase() || it.isDigit() || it == '_' })
}