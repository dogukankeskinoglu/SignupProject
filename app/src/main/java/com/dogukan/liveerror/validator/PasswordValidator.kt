package com.dogukan.liveerror.validator

import com.dogukan.liveerror.R
import com.dogukan.liveerror.common.enums.EnumValidator
import com.dogukan.liveerror.common.extension.validateLong
import com.dogukan.liveerror.common.extension.validateShort

class PasswordValidator : Validator {

    override fun validateSignUp(field: String) = when {
        field.isEmpty() -> R.string.passwordEmptyError
        field.validateShort(EnumValidator.MinLengthPassword.constraint) -> R.string.passwordShortError
        field.validateLong(EnumValidator.MaxLengthPassword.constraint) -> R.string.passwordLongError
        field.passwordContainDigitUpperCaseLowerCaseError() -> R.string.passwordContainDigitUpperCaseLowerCaseError
        field.passwordContainDigitUpperCaseSpecialCharError() -> R.string.passwordContainDigitUpperCaseSpecialCharError
        field.passwordContainDigitLowerCaseSpecialCharError() -> R.string.passwordContainDigitLowerCaseSpecialCharError
        field.passwordContainUpperCaseLowerCaseSpecialCharError() -> R.string.passwordContainUpperCaseLowerCaseSpecialCharError
        field.passwordContainDigitUpperCaseError() -> R.string.passwordContainDigitUpperCaseError
        field.passwordContainDigitLowerCaseError() -> R.string.passwordContainDigitLowerCaseError
        field.passwordContainDigitSpecialCharError() -> R.string.passwordContainDigitSpecialCharError
        field.passwordContainUpperCaseLowerCaseError() -> R.string.passwordContainUpperCaseLowerCaseError
        field.passwordContainUpperCaseSpecialCharError() -> R.string.passwordContainUpperCaseSpecialCharError
        field.passwordContainLowerCaseSpecialCharError() -> R.string.passwordContainLowerCaseSpecialCharError
        !field.passwordContainDigit() -> R.string.passwordContainDigitError
        !field.passwordContainUpperCase() -> R.string.passwordContainUpperCaseError
        !field.passwordContainLowerCase() -> R.string.passwordContainLowerCaseError
        !field.passwordContainSpecialCharacter() -> R.string.passwordContainSpecialCharError
        else -> null
    }

    fun validatePassword(first: String, second: String)=when{
        first!=second-> R.string.repasswordError
        else -> null
    }

    private fun String.passwordContainDigitUpperCaseLowerCaseError() =
        !passwordContainDigit() && !passwordContainUpperCase() && !passwordContainLowerCase() && passwordContainSpecialCharacter()

    private fun String.passwordContainDigitUpperCaseSpecialCharError() =
        !passwordContainDigit() && !passwordContainUpperCase() && passwordContainLowerCase() && !passwordContainSpecialCharacter()

    private fun String.passwordContainDigitLowerCaseSpecialCharError() =
        !passwordContainDigit() && passwordContainUpperCase() && !passwordContainLowerCase() && !passwordContainSpecialCharacter()

    private fun String.passwordContainUpperCaseLowerCaseSpecialCharError() =
        passwordContainDigit() && !passwordContainUpperCase() && !passwordContainLowerCase() && !passwordContainSpecialCharacter()

    private fun String.passwordContainDigitUpperCaseError() =
        !passwordContainDigit() && !passwordContainUpperCase() && passwordContainLowerCase() && passwordContainSpecialCharacter()

    private fun String.passwordContainDigitLowerCaseError() =
        !passwordContainDigit() && passwordContainUpperCase() && !passwordContainLowerCase() && passwordContainSpecialCharacter()

    private fun String.passwordContainDigitSpecialCharError() =
        !passwordContainDigit() && passwordContainUpperCase() && passwordContainLowerCase() && !passwordContainSpecialCharacter()

    private fun String.passwordContainUpperCaseLowerCaseError() =
        passwordContainDigit() && !passwordContainUpperCase() && !passwordContainLowerCase() && passwordContainSpecialCharacter()

    private fun String.passwordContainUpperCaseSpecialCharError() =
        passwordContainDigit() && !passwordContainUpperCase() && passwordContainLowerCase() && !passwordContainSpecialCharacter()

    private fun String.passwordContainLowerCaseSpecialCharError() =
        passwordContainDigit() && passwordContainUpperCase() && !passwordContainLowerCase() && !passwordContainSpecialCharacter()

    private fun String.passwordContainLowerCase() = any { it.isLowerCase() }

    private fun String.passwordContainUpperCase() = any { it.isUpperCase() }

    private fun String.passwordContainDigit() = any { it.isDigit() }

    private fun String.passwordContainSpecialCharacter() = any { !it.isLetterOrDigit() }
}