package com.dogukan.liveerror.signup.ui

import androidx.lifecycle.*
import com.dogukan.liveerror.validator.EmailValidator
import com.dogukan.liveerror.validator.PasswordValidator
import com.dogukan.liveerror.validator.UsernameValidator
import com.dogukan.liveerror.validator.Validator

class SignUpViewModel : ViewModel(){

    private val usernameValidator = UsernameValidator()
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()

    val username = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val repassword= MutableLiveData<String>()

    private val _usernameErrorMessage = MediatorLiveData<Int>()
    private val _emailErrorMessage = MediatorLiveData<Int>()
    private val _passwordErrorMessage = MediatorLiveData<Int>()
    private val _rePasswordErrorMessage= MediatorLiveData<Int>()

    val usernameErrorMessage: LiveData<Int> = _usernameErrorMessage
    val emailErrorMessage: LiveData<Int> = _emailErrorMessage
    val passwordErrorMessage: LiveData<Int> = _passwordErrorMessage
    val rePasswordErrorMessage: LiveData<Int> = _rePasswordErrorMessage

    private val _signUpState= MutableLiveData<Boolean>()
    val signUpState :LiveData<Boolean> = _signUpState

    private val _isKeyboardHide = MutableLiveData(false)
    val isKeyboardHide: LiveData<Boolean> = _isKeyboardHide

    private var _userHasClickedSignUpButton = false

    fun onSignUpButtonClick() {
        _isKeyboardHide.value = true
        if (!_userHasClickedSignUpButton) {
            setError()
        }
        _signUpState.value = isIdentifierValid() and isPasswordValid() and isEmailValid() and isRepasswordValid()
    }

    private fun isIdentifierValid(): Boolean {
        _usernameErrorMessage.value = usernameValidator.validateSignUp(username.value.orEmpty())
        return _usernameErrorMessage.value == null
    }

    private fun isPasswordValid(): Boolean {
        _passwordErrorMessage.value = passwordValidator.validateSignUp(password.value.orEmpty())
        return _passwordErrorMessage.value == null
    }

    private fun isRepasswordValid(): Boolean{
        _rePasswordErrorMessage.value = passwordValidator.validatePassword(password.value.orEmpty(),repassword.value.orEmpty())
        return _rePasswordErrorMessage.value == null
    }

    private fun isEmailValid(): Boolean {
        _emailErrorMessage.value = emailValidator.validateSignUp(email.value.orEmpty())
        return _emailErrorMessage.value == null
    }

    private fun MediatorLiveData<Int>.liveErrorMessage(
        data: MutableLiveData<String>,
        validatorType: Validator,
    ) {
        apply {
            addSource(data) {
                this.value = validatorType.validateSignUp(it)
            }
        }
    }

    private fun setError() {

        _userHasClickedSignUpButton = true
        _usernameErrorMessage.liveErrorMessage(username, usernameValidator)
        _emailErrorMessage.liveErrorMessage(email, emailValidator)
        _passwordErrorMessage.liveErrorMessage(password, passwordValidator)
        _rePasswordErrorMessage.apply {
            addSource(repassword){
                this.value=null
            }
        }
    }



}


