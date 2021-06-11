package com.dogukan.liveerror.common.extension

import android.content.Context

fun Int.resolveAsString(context: Context) = context.getString(this)