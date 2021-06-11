package school.cactus.succulentshop.validator

import android.os.Build
import com.dogukan.liveerror.R
import com.dogukan.liveerror.validator.PasswordValidator
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class PasswordUnitTest(private val password: String, private val error: Int?) {

    private val validator = PasswordValidator()

    @Test
    fun `given input password, when validate called, then if it is valid should return null else return error`() {

        // When
        val actual = validator.validateSignUp(password)

        // Then
        Truth.assertThat(actual).isEqualTo(error)
    }

    private companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters()
        fun passwordArguments() = listOf(
            arrayOf("", R.string.passwordEmptyError),
            arrayOf("aaaaaaa", R.string.passwordShortError),
            arrayOf("AAAA", R.string.passwordShortError),
            arrayOf("Ab*0BAb*0BAb*0BAb*0BAb*0BAb*0BAb*0BAb*0B", R.string.passwordLongError),
            arrayOf("Ab*0BAb*0BAb*0BAb*0BAb*0BAb*0BAb*0BAb*0BB", R.string.passwordLongError),
            arrayOf("************", R.string.passwordContainDigitUpperCaseLowerCaseError),
            arrayOf("lllllllllllll", R.string.passwordContainDigitUpperCaseSpecialCharError),
            arrayOf("ABCDEFGH", R.string.passwordContainDigitLowerCaseSpecialCharError),
            arrayOf("0000000000000", R.string.passwordContainUpperCaseLowerCaseSpecialCharError),
            arrayOf("missiere*", R.string.passwordContainDigitUpperCaseError),
            arrayOf("miss*", R.string.passwordShortError),
            arrayOf("MISSIERE*", R.string.passwordContainDigitLowerCaseError),
            arrayOf("sssssAAAA", R.string.passwordContainDigitSpecialCharError),
            arrayOf("01234567*", R.string.passwordContainUpperCaseLowerCaseError),
            arrayOf("aaaaaaaa0", R.string.passwordContainUpperCaseSpecialCharError),
            arrayOf("AAAAAAAAA0", R.string.passwordContainLowerCaseSpecialCharError),
            arrayOf("Missiere*", R.string.passwordContainDigitError),
            arrayOf("Mis*", R.string.passwordShortError),
            arrayOf("Miss*Miss*Miss*Miss*Miss*Miss*Miss*Miss**", R.string.passwordLongError),
            arrayOf("missiere0*", R.string.passwordContainUpperCaseError),
            arrayOf("MISSIERE*0", R.string.passwordContainLowerCaseError),
            arrayOf("Missiere0", R.string.passwordContainSpecialCharError),
            arrayOf("5Fs6wkydv0*", null),
            arrayOf("0123A*aa", null),
            arrayOf("Trendyol2009*", null),
        )
    }
}