package com.dogukan.liveerror

import android.os.Build
import com.dogukan.liveerror.validator.UsernameValidator
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UsernameUnitTest(private val username: String, private val error: Int?) {

    private val validator = UsernameValidator()

    @Test
    fun `given input username, when validate called, then if it is valid should return null else return error`() {

        // When
        val actual = validator.validateSignUp(username)

        // Then
        Truth.assertThat(actual).isEqualTo(error)
    }

    private companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters()
        fun usernameArguments() = listOf(
            arrayOf("", R.string.usernameEmptyError),
            arrayOf("Asa", R.string.usernameContainError),
            arrayOf("*missiere", R.string.usernameContainError),
            arrayOf("0skda", null),
            arrayOf("a", R.string.usernameShortError),
            arrayOf("ab", R.string.usernameShortError),
            arrayOf("aaaabaaaabaaaabaaaab", R.string.usernameLongError),
            arrayOf("ashdaA", R.string.usernameContainError),
            arrayOf("missiererere", null),
            arrayOf("missi", null),
            arrayOf("missieremissiereee", null),
        )
    }
}