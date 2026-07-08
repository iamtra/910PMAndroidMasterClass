package kh.com.pheaktra.developer.basic.android.util

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object BiometricUtil {

    /**
     * You can use sealed class or enum class
     */
    sealed class BiometricStatus {
        data object Available : BiometricStatus()
        data object NoHardware : BiometricStatus()
        data object HardwareUnavailable : BiometricStatus()
        data object NotEnrolled : BiometricStatus()
        data object SecurityUpdateRequired : BiometricStatus()
        data object Unsupported : BiometricStatus()
        data object Unknown : BiometricStatus()
    }

    private val authenticators: Int
        get() = BiometricManager.Authenticators.BIOMETRIC_STRONG

    fun checkBiometricAvailable(context: Context): BiometricStatus {
        val biometricManager = BiometricManager.from(context)

        return when (biometricManager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                BiometricStatus.Available
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                BiometricStatus.NoHardware
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                BiometricStatus.HardwareUnavailable
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                BiometricStatus.NotEnrolled
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                BiometricStatus.SecurityUpdateRequired
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                BiometricStatus.Unsupported
            }

            else -> {
                BiometricStatus.Unknown
            }
        }
    }

    fun showBiometricDialog(
        activity: FragmentActivity,
        title: String = "Biometric Authentication",
        subtitle: String = "Use your fingerprint or face to continue",
        negativeButtonText: String = "Cancel",
        onSuccess: () -> Unit,
        onFailed: () -> Unit,
        onError: (String) -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(activity)

        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailed()
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    onError(errString.toString())
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setAllowedAuthenticators(authenticators)
            .setNegativeButtonText(negativeButtonText)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}