package com.dogukan.liveerror.common.extension

fun String.validateShort(constraint: Int) = length <= constraint

fun String.validateLong(constraint: Int) = length >= constraint

fun String.validateEmail() = !(contains("@") && contains("."))