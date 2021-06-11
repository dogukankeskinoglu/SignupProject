package com.dogukan.liveerror

import android.os.Build
import com.dogukan.liveerror.validator.EmailValidator
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class EmailUnitTest(private val email: String, private val error: Int?) {

    private val validator = EmailValidator()

    @Test
    fun `given input email, when validate called, then if it is valid should return null else return error`() {

        // When
        val actual = validator.validateSignUp(email)

        // Then
        Truth.assertThat(actual).isEqualTo(error)
    }

    private companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "Email: {0}")
        fun emailArguments() = listOf(
            arrayOf("", R.string.emailEmptyError),
            arrayOf("dogukan@gmail.com", null),
            arrayOf("d", R.string.emailContainError),
            arrayOf("@.", R.string.emailShortError),
            arrayOf("sasdasddasdassasdasddasdassasdasddasdassasdasddasdassasdasddasdassasdasddasdassasdasddasdassasdasddasdassasdasddasdas@gmail.com",
                R.string.emailLongError),
            arrayOf("dad@gmail.com", null),
            arrayOf("dad@gmailcom", R.string.emailContainError),
            arrayOf("dad@gmail.com", null),
        )
    }
}