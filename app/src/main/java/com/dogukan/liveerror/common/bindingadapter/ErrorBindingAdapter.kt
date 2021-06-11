package com.dogukan.liveerror.common.bindingadapter

import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.dogukan.liveerror.common.extension.resolveAsString
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun TextInputLayout.error(@StringRes errorMessage: Int?) {
    error = errorMessage?.resolveAsString(context)
    isErrorEnabled = errorMessage != null
}